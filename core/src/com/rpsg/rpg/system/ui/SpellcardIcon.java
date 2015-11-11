package com.rpsg.rpg.system.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.rpsg.gdxQuery.$;
import com.rpsg.gdxQuery.CustomRunnable;
import com.rpsg.gdxQuery.GdxQuery;
import com.rpsg.rpg.core.Setting;
import com.rpsg.rpg.object.base.items.BaseItem;
import com.rpsg.rpg.object.base.items.Spellcard;
import com.rpsg.rpg.system.base.Res;

public class SpellcardIcon extends Actor{
	Label label;
	Image icon,bg;
	Spellcard sc;
	boolean select;
	GdxQuery query;
	
	public SpellcardIcon(Spellcard sc) {
		super();
		this.sc = sc;
		generateIcon(sc);
		label = new Label(sc.name,22);
		label.setText(sc.name);
		label.setAlignment(Align.center);
		query = $.add(bg = Res.get(Setting.UI_BASE_IMG));
	}
	
	public SpellcardIcon onClick(final CustomRunnable<SpellcardIcon> run){
		query.onClick(new Runnable(){
			public void run() {
				run.run(SpellcardIcon.this);
			}
		});
		return this;
	}
	
	public SpellcardIcon click(){
		query.click();
		return this;
	}
	
	public SpellcardIcon setText(String text) {
		label.setText(text);
		return this;
	}
	
	public SpellcardIcon generateIcon(BaseItem baseItem) {
		icon=Res.get(baseItem.getIcon()).disableTouch();
		return this;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(select){
			bg.setPosition(getX(), getY());
			bg.setSize(getWidth(), getHeight());
			bg.draw(batch);
		}
		label.setPosition((int)(getX()+getHeight()+25), (int)(getY()));
		label.setHeight(getHeight());
		icon.setPosition(getX(), getY());
		icon.setHeight(getHeight());
		icon.setWidth(getHeight());
		icon.draw(batch);
		label.draw(batch, parentAlpha);
		super.draw(batch, parentAlpha);
	}
	
	public SpellcardIcon select(boolean flag){
		select = flag;
		return this;
	}
}
