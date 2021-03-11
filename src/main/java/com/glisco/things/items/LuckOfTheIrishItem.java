package com.glisco.things.items;

import com.glisco.things.ThingsCommon;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.Collections;
import java.util.List;

public class LuckOfTheIrishItem extends TrinketItemWithOptionalTooltip {

    public LuckOfTheIrishItem() {
        super(new Settings().maxCount(1).group(ThingsCommon.THINGS_ITEMS));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return true;
    }

    @Override
    List<Text> getTooltipText() {
        return Collections.singletonList(new LiteralText("§7Kinda turns Poisonous Potatoes into Golden Apples"));
    }
}
