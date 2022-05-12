package com.miki4920.furnacemod.screen;

import com.miki4920.furnacemod.FurnaceMod;
import com.miki4920.furnacemod.block.custom.LavaPoweredFurnace;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;
import java.lang.reflect.Member;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, FurnaceMod.MOD_ID);

    public static final RegistryObject<MenuType<LavaPoweredFurnaceMenu>> LAVA_POWERED_FURNACE_MENU = registerMenuType(LavaPoweredFurnaceMenu::new, "lava_powered_station_menu");
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                 String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
