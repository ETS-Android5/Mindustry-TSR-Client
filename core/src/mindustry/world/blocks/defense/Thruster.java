package mindustry.world.blocks.defense;

import arc.graphics.g2d.*;
import arc.util.*;
import arc.math.*;
import mindustry.annotations.Annotations.*;
import mindustry.entities.units.*;
import mindustry.world.blocks.*;
import mindustry.gen.*;
import mindustry.entities.*;
import mindustry.content.*;

public class Thruster extends Wall{
    public @Load("@-top") TextureRegion topRegion;

    public Thruster(String name){
        super(name);
        rotate = true;
        quickRotate = false;
    }

    @Override
    public void drawRequestRegion(BuildPlan req, Eachable<BuildPlan> list){
        Draw.rect(region, req.drawx(), req.drawy());
        Draw.rect(topRegion, req.drawx(), req.drawy(), req.rotation * 90);
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, topRegion};
    }

    public class ThrusterBuild extends WallBuild implements ControlBlock{

        public @Nullable BlockUnitc unit;

        @Override
        public Unit unit(){
            if(unit == null){
                unit = (BlockUnitc)UnitTypes.block.create(team);
                unit.tile(this);
            }
            return (Unit)unit;
        }

        @Override
        public boolean canControl(){
            return true;
        }

        @Override
        public boolean shouldAutoTarget(){
            return false;
        }

        @Override
        public void draw(){
            super.draw();

            Draw.rect(topRegion, x, y, rotdeg());
        }
    }
}
