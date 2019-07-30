package com.Zoko061602.ThaumicRestoration.lib.research;

import net.minecraft.block.Block;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry.EnumResearchMeta;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.api.research.theorycraft.TheorycraftCard;
import thaumcraft.api.research.theorycraft.TheorycraftManager;
import thaumcraft.common.lib.research.theorycraft.CardAwareness;
import thaumcraft.common.lib.research.theorycraft.CardFocus;

public class ResearchHelper {

	private static class REB extends ResearchEntryBuilder{}
	public static class RSB extends ResearchStageBuilder{}
	public static class RAB extends ResearchAddendumBuilder{}



	public static void makeResearch(String tag, String tab, String name, int Xpos, int Ypos, Object icon,ResearchStage[] stages,String[] parents,ResearchAddendum[] add, EnumResearchMeta... meta) {
		 ResearchEntryBuilder reb = new REB().setBaseInfo(tag, tab, name, Xpos, Ypos, icon);
		 reb.setStages(stages);
         reb.setParents(parents);
         reb.setMeta(meta);
         if(add!=null)
        	 reb.setAddenda(add);
         reb.buildAndRegister();
		}

	public static void makeRestorationResearch(String tag,String name, int Xpos, int Ypos,Object icon, ResearchStage[] stages, String[] parents, EnumResearchMeta... meta) {
		makeResearch(tag, "RESTORATION", name, Xpos, Ypos, icon, stages, parents, null, meta);
	}

	public static void makeRestorationResearch(String tag,String name, int Xpos, int Ypos,Object icon, ResearchStage[] stages, String[] parents,ResearchAddendum[] add, EnumResearchMeta... meta) {
			makeResearch(tag, "RESTORATION", name, Xpos, Ypos, icon, stages, parents, add, meta);
		}

	public static void makeAid(Block block, Class<? extends TheorycraftCard>... cards) {
		if(TheorycraftManager.aids.get(block.getUnlocalizedName())==null)
         TheorycraftManager.aids.put(block.getUnlocalizedName(), new AidBase(block, cards));
	}


}
