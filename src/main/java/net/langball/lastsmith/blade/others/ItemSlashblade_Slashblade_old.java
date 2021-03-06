package net.langball.lastsmith.blade.others;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.recipe.RecipeAwakeBladeTLS;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Slashblade_old {
	   @SubscribeEvent
	   public void init(InitEvent event) {
	      String name = "flammpfeil.slashblade.named.slashblade_old";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(51));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag, 6.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/slashblade/1");
	      ItemSlashBlade.ModelName.set(tag, "named/slashblade/1");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(264));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      ItemStack custombladeReqired = new ItemStack(SlashBlade.weapon);
	      ItemStack ingot = SlashBlade.findItemStack("flammpfeil.slashblade", "ingot_bladesoul", 1);
	      
	      SlashBlade.addRecipe(name, new RecipeAwakeBladeTLS(new ResourceLocation(SlashBlade.modid,name),customblade, custombladeReqired, new Object[]{" AD", " AD", " B ", Character.valueOf('A'), ingot, Character.valueOf('B'), custombladeReqired, Character.valueOf('D'), new ItemStack(Blocks.LAPIS_BLOCK)}));

	   }

}
