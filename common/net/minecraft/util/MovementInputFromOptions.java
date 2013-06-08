package net.minecraft.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

@SideOnly(Side.CLIENT)
public class MovementInputFromOptions extends MovementInput
{
	private GameSettings gameSettings;
    private long l;
    private boolean g;

    public MovementInputFromOptions(GameSettings par1GameSettings)
    {
        this.gameSettings = par1GameSettings;
        this.l = System.currentTimeMillis();
        this.g = false;
    }

    public void updatePlayerMoveState()
    {
        this.moveStrafe = 0.0F;
        this.moveForward = 0.0F;
        // movement input 
        if (this.gameSettings.keyBindForward.pressed)
        {
            ++this.moveForward;
        }

        if (this.gameSettings.keyBindBack.pressed)
        {
            --this.moveForward;
        }

        if (this.gameSettings.keyBindLeft.pressed)
        {
            ++this.moveStrafe;
        }

        if (this.gameSettings.keyBindRight.pressed)
        {
            --this.moveStrafe;
        }
        //end
        
        //jump
        this.jump = this.gameSettings.keyBindJump.pressed;
        //end
        
        
        long var1;
        
        //toggleSneak
        if (this.g != this.gameSettings.keyBindSneak.pressed && Minecraft.getMinecraft().playerController.isNotCreative())
        {
            var1 = System.currentTimeMillis();

            if (this.gameSettings.keyBindSneak.pressed)
            {
                this.sneak = !this.sneak;
            }
            else if (var1 - this.l > 300L)
            {
                this.sneak = false;
            }

            this.l = var1;
            this.g = this.gameSettings.keyBindSneak.pressed;
        }
        //end

        //Sneak
        if (this.g != this.gameSettings.keyBindSneak.pressed && Minecraft.getMinecraft().playerController.isInCreativeMode())
        {
            var1 = System.currentTimeMillis();
            this.sneak = this.gameSettings.keyBindSneak.pressed;
            this.l = var1;
            this.g = this.gameSettings.keyBindSneak.pressed;
        }
        //end

        //sneakcheck
        if (this.sneak)
        {
            this.moveStrafe = (float)((double)this.moveStrafe * 0.3D);
            this.moveForward = (float)((double)this.moveForward * 0.3D);
        }
        //end
    }
}
