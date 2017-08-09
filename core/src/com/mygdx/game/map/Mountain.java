package com.mygdx.game.map;

/** 分型算法，生成高度图
 * @author ttwings
 * @version 0.1
 * @since 2013-8-6 上午9:42:27 */
public class Mountain{
	private int width;
	private int height;
	private int[][] mapIs;
	/** 中间点的随机位移距离 就是陡峭程度
	 * @param num
	 * @return */
	float Displace(float num) {
		float max= num /(width+height)*3f;
		return (float)(Math.random()-0.5f)*max;
	}
	/**
	 * @param width
	 * @param height
	 */
	void Drawcloud(int width,int height){
		mapIs = new int[width][height];
		this.width = width;
		this.height = height;
		float r1, r2, r3, r4;
		 r1=0.1f;
		 r2=0.3f;
		 r3=0.5f;
		 r4=0.7f;
		Midpoint(0,0,width,height,r1,r2,r3,r4);
	}
	/** 中间点的计算
	 * @param x 起始迭代点x坐标
	 * @param y 起始迭代点y坐标
	 * @param width
	 * @param height
	 * @param r1 左上角值
	 * @param r2 右上角值
	 * @param r3 右下角值
	 * @param r4 左下角值*/
	void Midpoint(float x, float y, float width, float height, float r1, float r2, float r3, float r4) {
		float M1, M2, M3, M4, Middle;
		float newWidth=width/2;
		float newHeight=height/2;
//		当迭代到半径为2的时候停止
		if(width>=2||height>=2){
			Middle=(r1+r2+r3+r4)/4+Displace(newWidth+newHeight);
			M1=(r1+r2)/2; // 计算其他几个点的值
			M2=(r2+r3)/2;
			M3=(r3+r4)/2;
			M4=(r4+r1)/2;
			if(Middle<0){
				Middle=0;
			}else if(Middle>1.0f){
				Middle = 1;
				
			}
			// 画剩下的点，迭代处理。1 分4 这样迭代
			Midpoint(x,y,newWidth,newHeight,r1,M1,Middle,M4);
			Midpoint(x+newWidth,y,newWidth,newHeight,M1,r2,M2,Middle);
			Midpoint(x+newWidth,y+newHeight,newWidth,newHeight,Middle,M2,r3,M3);
			Midpoint(x,y+newHeight,newWidth,newHeight,M4,Middle,M3,r4);
		}else{
			float c=(r1+r2+r3+r4)/4;
			// 32 这里设定最大高度。
			mapIs[(int)x][(int)y]=(byte)(c*64);
		}
	}
	public int[][] getMapIs(int width, int height,int z) {
		Drawcloud(width,height);
		return mapIs;
	}
}
