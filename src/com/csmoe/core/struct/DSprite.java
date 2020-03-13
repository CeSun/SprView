package com.csmoe.core.struct;

public class DSprite {
	
	public enum AngleType {
		SPR_FWD_PARALLEL_UPRIGHT,
		SPR_FACING_UPRIGHT,
		SPR_FWD_PARALLEL,
		SPR_ORIENTED,
		SPR_FWD_PARALLEL_ORIENTED
	}
	public enum DrawType {
		SPR_NORMAL,
		SPR_ADDITIVE,
		SPR_INDEXALPHA,
		SPR_ALPHTEST
	}
	public enum FaceType {
		SPR_CULL_FRONT,
		SPR_CULL_NONE
	}
	public enum SyncType {
		ST_SYNC,
		ST_RAND
	}
	
	public final static int IDSPRITEHEADER = (('P'<<24)+('S'<<16)+('D'<<8)+'I');
	public final static int SPRITE_VERSION = 2;
	
	public int ident;
	public int version;
	public int type;
	public int texFormat;
	public float boundingradius;
	public int width;
	public int height;
	public int	numframes;
	public float beamlength;
	public int synctype;
	
	
	
	
}
