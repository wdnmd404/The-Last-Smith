package net.langball.lastsmith;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.langball.lastsmith.blade.*;
import net.langball.lastsmith.blocks.BlockLoader;
import net.langball.lastsmith.blocks.TileEntityLoader;
import net.langball.lastsmith.compat.*;
import net.langball.lastsmith.gui.GuiLoader;
import net.langball.lastsmith.items.ItemLoader;
import net.langball.lastsmith.packet.PacketKeyMessage;
import net.langball.lastsmith.packet.PacketKeyMessageHandler;
import net.langball.lastsmith.recipe.RecipeLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import vazkii.botania.api.BotaniaAPI;

public class CommonProxy {
	public static CreativeTabs tab;
    private static SimpleNetworkWrapper network;
    public static SimpleNetworkWrapper getNetwork() {
        return network;
    }
	 public void preInit(FMLPreInitializationEvent event)
	    {
		 new ConfigLoader(event);		
		 tab=new CreativeTabsSmith();
		 new BlockLoader(event);
		 new ItemLoader(event);
		 new TileEntityLoader(event);
		 new BladeLoader(event);
		 MinecraftForge.EVENT_BUS.register(new EventKillingMob());
}
    public void init(FMLInitializationEvent event)
    { 
    	new GuiLoader();
    	new RecipeLoader();
    	if(Loader.isModLoaded(Thaumcraft.MODID)){
    		 ThaumcraftApi.registerResearchLocation(new ResourceLocation(Last_worker.MODID+":research/research.json"));
        	 ResearchCategories.registerCategory("KATANA", null, new AspectList().add(Aspect.SOUL, 1), new ResourceLocation(Last_worker.MODID+":textures/research/yamatooo.png"), new ResourceLocation(Last_worker.MODID+":textures/research/guislashblade.jpg"));
    	}
    	network = NetworkRegistry.INSTANCE.newSimpleChannel(Last_worker.MODID);
    	network.registerMessage(new PacketKeyMessageHandler(),PacketKeyMessage.class,0,Side.SERVER);
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    	if(Loader.isModLoaded("botania")){
    		ItemStack sphere = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);

    		ItemStack vineBall = SlashBlade.findItemStack("Botania", "vineBall", 1);
    		ItemStack thornChakram = SlashBlade.findItemStack("Botania", "thornChakram", 1);
    		SlashBladeHooks.EventBus.register(new ManaRepair());
    		BotaniaAPI.registerRuneAltarRecipe(SlashBlade.findItemStack("flammpfeil.slashblade", "flammpfeil.slashblade.named.yingjian", 1), 1000, new Object[] { SlashBlade.findItemStack("flammpfeil.slashblade", "flammpfeil.slashblade.named.roukan", 1), sphere, "gaiaIngot", vineBall, new ItemStack(Blocks.LOG), thornChakram, "ingotTerrasteel", new ItemStack(Items.STICK) });
    	}
    }

}
