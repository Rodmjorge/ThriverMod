package net.rodmjorgeh.renovay.data.loot.modifiers;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import net.rodmjorgeh.renovay.data.loot.GlobalLootDataGenerator;

import java.util.List;
import java.util.Optional;

public class AddLootPoolModifier extends LootModifier {

    public static final MapCodec<AddLootPoolModifier> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    IGlobalLootModifier.LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(x -> x.conditions),
                    (LootPool.CODEC.listOf().xmap(list -> list.toArray(LootPool[]::new), List::of))
                            .fieldOf("pools").forGetter(x -> x.pools)
            )
            .apply(instance, AddLootPoolModifier::new)
    );

    private final LootPool[] pools;

    public AddLootPoolModifier(LootItemCondition[] conditions, LootPool[] pools) {
        super(conditions);
        this.pools = pools;
    }

    public LootPool[] getPools() {
        return this.pools;
    }

    /**
     * When the loot table begins generating, this method gets called to add to that loot table. However, that loot
     * table won't have the new loot pools, so a new generated loot is created which basically regenerates the loot
     * table again, so it has the new pools created via {@link GlobalLootDataGenerator}.
     */
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ObjectArrayList<ItemStack> newGeneratedLoot = new ObjectArrayList<>();
        Optional<ResourceKey<LootTable>> location = BuiltInLootTables.all().stream().filter(
                x -> x.location().equals(context.getQueriedLootTableId())
        ).findFirst();

        if (location.isEmpty()) {
            return generatedLoot;
        }

        for (LootItemCondition cond : this.conditions) {
            if (!cond.test(context)) {
                return generatedLoot;
            }
        }

        context.getResolver().lookupOrThrow(Registries.LOOT_TABLE).get(location.get()).ifPresent(refTable -> {
            LootTable table = refTable.value();
            for (LootPool pool : this.getPools()) {
                if (table.getPool(pool.getName()) == null) {
                    table.addPool(pool);
                }
            }

            table.getRandomItemsRaw(context, LootTable.createStackSplitter(context.getLevel(), newGeneratedLoot::add));
        });
        return newGeneratedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
