package com.rpsg.rpg.core;

import java.io.Serializable;

/**
 * GDX-RPG 系统设置<br>
 * 从 {@link RPG#setting} 变量中访问
 */
public class Setting implements Serializable{
	private static final long serialVersionUID = -2067767488295945018L;
	
	
	/**是否进行纹理过滤*/
	public boolean filter = true;
	
	/**总音量*/
	public float volume = 1f;
	/**音效音量*/
	public float SEVolume = 1f;
	/**音乐音量*/
	public float musicVolume = 1f;
	
	/**是否启用更清晰的字体<br>
	 * see {@link Text#get(int, com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator) Text.get}
	 * */
	public boolean hdFont = false;
	
	/**是否允许缓存资源（如纹理、图片）以提高游戏速度*/
	public boolean cache = true;
	
	/**
	 * 创建系统设置，如果硬盘已存在则直接读取
	 */
	public static Setting create() {
		Object obj = File.load(Path.SAVE + "/setting.sav");
		if(obj == null)
			return new Setting();
		return (Setting)obj; 
	}
}
