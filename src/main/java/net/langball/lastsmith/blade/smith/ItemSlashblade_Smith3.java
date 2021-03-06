package net.langball.lastsmith.blade.smith;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.langball.lastsmith.blade.BladeLoader;
import net.langball.lastsmith.blade.ItemSlashBladeNamedSS;
import net.langball.lastsmith.recipe.RecipeBx;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSlashblade_Smith3 {
		@SubscribeEvent
	   public void init(InitEvent event) {
		  String name = "flammpfeil.slashblade.named.smith_3";
	      String name2 = "flammpfeil.slashblade.named.smith_2";
	      ItemStack customblade = new ItemStack(BladeLoader.bladeNamed, 1, 0);
	      NBTTagCompound tag = new NBTTagCompound();
	      customblade.setTagCompound(tag);
	      ItemSlashBladeNamedSS.CurrentItemName.set(tag, name);
	      ItemSlashBladeNamedSS.CustomMaxDamage.set(tag, Integer.valueOf(95));
	      ItemSlashBlade.AttackAmplifier.set(tag, 2F);
	      ItemSlashBlade.setBaseAttackModifier(tag, 16.0F);
	      ItemSlashBlade.TextureName.set(tag, "named/smith/smith_2");
	      ItemSlashBlade.ModelName.set(tag, "named/smith/model");
	      ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(0));
	      BladeLoader.registerCustomItemStack(name, customblade);
	      ItemSlashBladeNamedSS.NamedBlades.add(name);
	      customblade.addEnchantment(Enchantments.POWER, 6);
	      customblade.addEnchantment(Enchantments.UNBREAKING,8);
	      customblade.addEnchantment(Enchantments.SHARPNESS,8);
	      ItemStack reqiredMain = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.bamboolight_yin");
	      ItemStack reqiredSub = BladeLoader.getCustomBlade("flammpfeil.slashblade.named.roukan");
	      ItemStack sb = BladeLoader.getCustomBlade(name2);
	      NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(sb);
	      ItemSlashBlade.KillCount.set(reqTag, Integer.valueOf(1000));
	      ItemSlashBlade.ProudSoul.set(reqTag, Integer.valueOf(5000));
	      ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(5));
	      
	      SlashBlade.addRecipe(name, new RecipeBx(new ResourceLocation(name),
	    		  BladeLoader.getCustomBlade(name),
	    		  sb, 1, 1,
	    		  reqiredMain, 0, 1, false,
	    		  reqiredSub, 2, 1, false, new Object[]{
	    		    "IGI", "ABC", "IGI",
	    			'I',"blockIron",
	    			'G',"blockSakura",
	    			'A',reqiredMain,
	    			'B',sb,
	    			'C',reqiredSub
	    		  }
	      ));
	   }
	   }

