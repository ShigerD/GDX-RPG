package com.rpsg.rpg.core;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.ScriptableObject;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.rpsg.rpg.controller.ArchiveController;
import com.rpsg.rpg.controller.GamePropertiesController;
import com.rpsg.rpg.view.GameView;

/**
 *	GDX-RPG 游戏上下文
 */
public class Game {
	public static final int STAGE_WIDTH = 1280, STAGE_HEIGHT = 720;
	
	/**游戏设置*/
	public static Setting setting;
	/**存档管理器*/
	public static ArchiveController archive;
	/**当前游戏视窗，如果是null的话代表还没在游戏里*/
	public static GameView view;
	/**游戏配置管理器*/
	public static GamePropertiesController prop;
	
	/**初始化*/
	public static void init(){
		setting = Setting.create();
		archive = new ArchiveController();
	}
	
	/**运行一段JS脚本, 可以传入一个变量来当做当前脚本的prototype*/
	public static Object executeJS(String js, Object prototype){
		if(js == null || js.trim().length() == 0) return null;
		
		try {
			Context ctx = Context.enter();
			//如果是在手机运行的，则不预编译脚本，否则会出现异常
			if(Game.isMobile())
				ctx.setOptimizationLevel(-1);
			
			ScriptableObject scope = ctx.initStandardObjects();
			
			if(prototype != null)
				scope.setPrototype(((NativeJavaObject)Context.javaToJS(prototype, scope)));
			
			Object obj = ctx.evaluateString(scope, js, null, 1, null);
			
			Context.exit();
			return obj;
			
		} catch (Exception e) {
			Log.e("无法执行脚本", e);
			e.printStackTrace();
			return null;
		}
	}
	
	public static int width(){
		return Gdx.graphics.getWidth();
	}
	
	public static int height(){
		return Gdx.graphics.getHeight();
	}
	
	/**是否正在手机中运行*/
	public static boolean isMobile(){
		return Gdx.app.getType() == ApplicationType.Android || Gdx.app.getType() == ApplicationType.iOS;
	}
	
	/**
	 * 获取一个默认{@link Stage}，他将和{@link Views#batch}共用一个画笔。<br>
	 * 要注意的是，这个画笔的{@link com.badlogic.gdx.graphics.g2d.Batch#setTransformMatrix(com.badlogic.gdx.math.Matrix4) transform}被改变的话，将可能导致接下来绘制的东西坐标出现异常。
	 */
	public static Stage stage(){
		return new Stage(new ScalingViewport(Scaling.stretch, Game.STAGE_WIDTH, Game.STAGE_HEIGHT, new OrthographicCamera()), Views.batch);
	}
}
