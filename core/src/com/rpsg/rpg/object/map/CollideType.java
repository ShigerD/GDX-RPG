package com.rpsg.rpg.object.map;

/**
 * 碰撞类型
 */
public enum CollideType {
	face,	//face碰撞，当两个MapSprite以在一个方格内且以面对面的姿态时，则会触发。
	near,	//near碰撞，当一个MapSprite在另一个MapSprite上下左右四周一格以内时，则会触发。
	facez,	//face+z碰撞，在face碰撞的基础上按下键盘的z键，则会触发。
	z,		//z碰撞，在near基础上，一个MapSprite面对另一个MapSprite并按下z时碰撞。
	foot,	//foot碰撞，一个MapSprite在另一个MapSprite的正上方（xy相同，layer(z)+1时就是正上方)。
	auto	//auto碰撞，无限循环某个脚本直到脚本手动停止/切换地图/游戏退出。
}
