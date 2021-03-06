package net.langball.lastsmith.blade;

import java.util.EnumSet;
import java.util.List;

import com.google.common.collect.Lists;

import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.TagPropertyAccessor.TagPropertyBoolean;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.ResourceLocationRaw;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSlashBladeNamedSS extends ItemSlashBlade{
	 public ItemSlashBladeNamedSS(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
	        super(par2EnumToolMaterial, baseAttackModifiers);
	    }
	    static public TagPropertyAccessor.TagPropertyBoolean IsFakeBlade = new TagPropertyBoolean("IsFakeBlade");
	    static public TagPropertyAccessor.TagPropertyBoolean IsDefaultBewitched = new TagPropertyAccessor.TagPropertyBoolean("isDefaultBewitched");
	    static public TagPropertyAccessor.TagPropertyString CurrentItemName = new TagPropertyAccessor.TagPropertyString("CurrentItemName");
	    static public TagPropertyAccessor.TagPropertyInteger CustomMaxDamage = new TagPropertyAccessor.TagPropertyInteger("CustomMaxDamage");
	    static public TagPropertyAccessor.TagPropertyString CurrentSheathName = new TagPropertyAccessor.TagPropertyString("CurrentSheathName");
	    static public TagPropertyAccessor.TagPropertyString CurrentBladeName = new TagPropertyAccessor.TagPropertyString("CurrentBladeName");
	    static public final String RepairOreDicMaterialStr = "RepairOreDicMaterial";
	    static public final String RepairMaterialNameStr = "RepairMaterialName";
	    private static ResourceLocationRaw texture = new ResourceLocationRaw("flammpfeil.slashblade","model/white.png");
		public ResourceLocationRaw getModelTexture() {
			return texture;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void addInformation(ItemStack arg0, World arg1, List arg2, ITooltipFlag arg3) {
			NBTTagCompound nbt = getItemTagCompound(arg0);
			super.addInformation(arg0, arg1, arg2, arg3);
			if(isActive(arg0)){
				arg2.add(TextFormatting.GOLD + I18n.format("blades.crafter")+":"+TextFormatting.GRAY+getname(nbt));	
				}
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
	    public void addInformationSwordClass(ItemStack par1ItemStack,
				EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
			NBTTagCompound tag = getItemTagCompound(par1ItemStack);
			if(IsFakeBlade.get(tag)){
				par3List.add(I18n.format("flammpfeil.swaepon.info.fake"));
			}else
			super.addInformationSwordClass(par1ItemStack, par2EntityPlayer, par3List, par4);
		}
	    public ResourceLocationRaw getModelTexture(ItemStack par1ItemStack){
	        NBTTagCompound tag = getItemTagCompound(par1ItemStack);
	        if(TextureName.exists(tag)){
	            String textureName = TextureName.get(tag);
	            ResourceLocationRaw loc;
	            if(!textureMap.containsKey(textureName))
	            {
	                loc = new ResourceLocationRaw("flammpfeil.slashblade","model/" + textureName + ".png");
	                textureMap.put(textureName,loc);
	            }else{
	                loc = textureMap.get(textureName);
	            }
	            return loc;
	        }
	        return ((ItemSlashBladeNamedSS)par1ItemStack.getItem()).getModelTexture();
	    }
		
	    @Override
	    public String getUnlocalizedName(ItemStack par1ItemStack) {
	        String result = super.getUnlocalizedName(par1ItemStack);
	        if(par1ItemStack.hasTagCompound()){
	            NBTTagCompound tag = par1ItemStack.getTagCompound();
	            if(CurrentItemName.exists(tag)){
	                result = "item." + CurrentItemName.get(tag);
	            }
	        }
	        return result;
	    }

		@Override
	    public int getMaxDamage(ItemStack stack) {
	        NBTTagCompound tag = getItemTagCompound(stack);
	        return CustomMaxDamage.get(tag,super.getMaxDamage(stack));
	    }

	    public static List<String> NamedBlades = Lists.newArrayList();

	    @Override
	    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
	        if (!this.isInCreativeTab(tab)) return;
	        if (this.isInCreativeTab(CreativeTabs.COMBAT)) return;
	        for(String bladename : NamedBlades){
	            ItemStack blade = BladeLoader.getCustomBlade(bladename);
	            if(blade.getItemDamage() == OreDictionary.WILDCARD_VALUE)
	                blade.setItemDamage(0);
	            if(!blade.isEmpty()) subItems.add(blade);
	        }
	    }

		@Override
		public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
			super.onCreated(stack, worldIn, playerIn);
			NBTTagCompound nbt = stack.getTagCompound();
			setPlayer(nbt,playerIn);
			setActive(nbt, true);
		}
		public static boolean isActive(ItemStack stack)
		  {
		    NBTTagCompound nbt = getOrCreateNbtData(stack);
		    return isActive(nbt);
		  }

		  public static String getname(NBTTagCompound nbt)
		  {
		    return nbt.getString("craftername");
		  }
		  public static void setActive(NBTTagCompound nbt, boolean active)
		  {
		    nbt.setBoolean("active", active);
		  }
		  public static void setPlayer(NBTTagCompound nbt, EntityPlayer playerIn)
		  {
			  nbt.setString("craftername", playerIn.getName().toString());
		  }
		  public static boolean isActive(NBTTagCompound nbt)
		  {
		    return nbt.getBoolean("active");
		  }
		 public static NBTTagCompound getOrCreateNbtData(ItemStack itemStack) {
		      NBTTagCompound ret = itemStack.getTagCompound();
		      if(ret == null) {
		         ret = new NBTTagCompound();
		         itemStack.setTagCompound(ret);
		      }
		      return ret;
		   }
	    
		@Override
	    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	    {
	        Boolean result = super.getIsRepairable(par1ItemStack,par2ItemStack);

	        NBTTagCompound tag = getItemTagCompound(par1ItemStack);

	        if(!result && tag.hasKey(RepairOreDicMaterialStr))
	        {
	            String oreName = tag.getString(RepairOreDicMaterialStr);
	            List<ItemStack> list = OreDictionary.getOres(oreName);
	            for(ItemStack curItem : list){
	                if(curItem.getItemDamage() == OreDictionary.WILDCARD_VALUE){
	                    result = curItem.getItem() == par2ItemStack.getItem();
	                }else{
	                    result = curItem.isItemEqual(par2ItemStack);
	                }
	                if(result)
	                    break;
	            }
	        }

	        if(!result && tag.hasKey(RepairMaterialNameStr))
	        {
	            String matName = tag.getString(RepairMaterialNameStr);
	            Item material = (Item)Item.REGISTRY.getObject(new ResourceLocationRaw(matName));
	            if(material != null)
	                result = par2ItemStack.getItem() == material;
	        }

	        return result;
	    }
		@Override
		public EnumSet<SwordType> getSwordType(ItemStack itemStack) {
			EnumSet<SwordType> set = super.getSwordType(itemStack);
			NBTTagCompound tag = getItemTagCompound(itemStack);
			if(IsFakeBlade.get(tag)){
				set.remove(SwordType.Enchanted);
				set.remove(SwordType.Bewitched);
				set.remove(SwordType.SoulEeater);
			}
			return set;
		}
}
