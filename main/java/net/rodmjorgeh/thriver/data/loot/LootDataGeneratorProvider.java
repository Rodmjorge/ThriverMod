package net.rodmjorgeh.thriver.data.loot;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.rodmjorgeh.thriver.data.DataGeneratorProvider;

import java.util.List;
import java.util.stream.Stream;

public interface LootDataGeneratorProvider<T> extends DataGeneratorProvider {
    DeferredRegister<T> getRegistry();

    default Stream<T> registryStream() {
        return this.getRegistry().getEntries().stream()
                .map(x -> (T)x.value());
    }
    default List<T> registryList() {
        return this.registryStream().toList();
    }
}
