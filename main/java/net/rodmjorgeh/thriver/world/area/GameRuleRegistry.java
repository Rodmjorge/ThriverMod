package net.rodmjorgeh.thriver.world.area;

import net.minecraft.world.level.GameRules;

public class GameRuleRegistry {

    public static GameRules.Key<GameRules.BooleanValue> RULE_DO_DOLLS_EYES_BLIND;

    public static void registerAll() {
        RULE_DO_DOLLS_EYES_BLIND = register(
                "doDollsEyesBlind", GameRules.Category.PLAYER, GameRules.BooleanValue.create(true)
        );
    }

    public static <T extends GameRules.Value<T>> GameRules.Key<T> register(String name, GameRules.Category category, GameRules.Type<T> type) {
        return GameRules.register(name, category, type);
    }
}
