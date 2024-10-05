package com.match3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;

public class RecordsScreen extends ScreenAdapter {
    private final FileHandle file;
    private final MatchThree game;
    private final OrderedMap<Integer, String> recordsMap = new OrderedMap();

    public RecordsScreen(MatchThree game) {
        this.game = game;
        this.file = game.getRecordsFile();
    }

    public void show() {
        final GameWindow window = this.game.getWindow();
        final Table mainTable = new Table();
        Label header = new Label("Records", this.game.getSkin());
        mainTable.add(header).align(1);
        mainTable.row();
        ScrollPane scrollPane = new ScrollPane((Actor)null, this.game.getSkin(), "white-bg");
        scrollPane.setFadeScrollBars(false);
        scrollPane.setVariableSizeKnobs(false);
        this.readRecords();
        final Label recordLabel = new Label("No winners yet...", this.game.getSkin());
        recordLabel.setAlignment(1);
        if (!this.recordsMap.isEmpty()) {
            StringBuilder text = new StringBuilder();
            ObjectMap.Entries var7 = this.recordsMap.iterator();

            while(var7.hasNext()) {
                ObjectMap.Entry<Integer, String> record = (ObjectMap.Entry)var7.next();
                text.append(String.format("%-40s%-6d%n", record.value, record.key));
            }

            recordLabel.setText(text.toString());
        }
        scrollPane.setActor(recordLabel);
        mainTable.add(scrollPane).width(300.0F).height(360.0F).padBottom(28.0F);
        mainTable.row();
        Table table = new Table();
        TextButton clearBtn = new TextButton("Clear", this.game.getSkin());
        table.defaults().space(15.0F).width(clearBtn.getWidth() * 4.0F).height(clearBtn.getHeight() * 2.0F);
        clearBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (RecordsScreen.this.file.exists()) {
                    RecordsScreen.this.file.delete();
                }
                recordLabel.setText("No winners yet...");
            }
        });
        table.add(clearBtn);
        table.row();
        TextButton recordsBtn = new TextButton("Menu", this.game.getSkin());
        recordsBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                window.removeActor(mainTable);
                RecordsScreen.this.game.setScreen(new MenuScreen(RecordsScreen.this.game));
            }
        });
        table.add(recordsBtn);
        table.row();
        TextButton exitBtn = new TextButton("Exit", this.game.getSkin());
        exitBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        table.add(exitBtn);
        mainTable.add(table);
        window.add(mainTable);
    }

    private void readRecords() {
        if (this.file.exists()) {
            String[] records = this.file.readString().split("\n");

            for(int i = 0; i < records.length - 1; i += 2) {
                int time = Integer.parseInt(records[i]);
                String name = records[i + 1];
                this.recordsMap.put(time, name);
            }

            this.recordsMap.orderedKeys().sort();
            this.recordsMap.orderedKeys().reverse();
        }

    }
}

