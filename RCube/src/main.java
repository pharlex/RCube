import javafx.stage.*;

import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class main extends Application {
	
	private int btnCol = 0;
	private int btnRow = 0;
	private long start;

	public static void main(String[] args) {
		
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage){
		Pane pane = new Pane();
		pane.setMinHeight(700);
		pane.setMinWidth(700);
		
		//create the cube and mix it up for the game
		Cube cube = new Cube();
		cube.mixUp();
		
		Button[][] btns = new Button[3][3];//array of buttons to represent the side of the cube you are looking at
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				
				//initialize button, locations, sizes, and id's
				btns[i][j] = new Button();
				btns[i][j].setLayoutX(50*i+175);
				btns[i][j].setLayoutY(50*j+175);
				btns[i][j].setScaleX(3);
				btns[i][j].setScaleY(2);
				double locx = btns[i][j].getLayoutX();
				double locy = btns[i][j].getLayoutY();
				int col = i;
				int row = j;
				
				
				btns[i][j].setOnMouseReleased(me->{//set the event for dragging the row or column
					btnCol = col;
					btnRow = row;
					if(me.getSceneX()>locx&&me.getSceneX()-locx>50){//mouse was dragged to the right, so rotate row to the right
						int[] s = new int[4];
						
						int side = cube.getCurSide();//use to track the turn
						for(int k=0; k<4; k++){//add the sides to be rotated to the list
							s[k] = cube.getSides()[side].getSideID();
							side = cube.getSides()[side].getRight();
						}
						
 						cube.rotate(s, btnRow, 1);//rotate the row selected
 						colorBtns(btns, cube);//change the colors to match the side that is showing in the cube
					}
					else if(me.getSceneX()<locx&&me.getSceneX()-locx<50){
						int[] s = new int[4];
						
						int side = cube.getCurSide();//use to track the turn
						for(int k=0; k<4; k++){//add the sides to be rotated to the list
							s[k] = cube.getSides()[side].getSideID();
							side = cube.getSides()[side].getLeft();
						}
						
 						cube.rotate(s, btnRow, 1);//rotate the row selected
 						colorBtns(btns, cube);//change the colors to match the side that is showing in the cube
					
					}
					else if(me.getSceneY()>locy&&me.getSceneY()-locy>50){
						int[] s = new int[4];
						
						int side = cube.getCurSide();//use to track the turn
						for(int k=0; k<4; k++){//add the sides to be rotated to the list
							s[k] = cube.getSides()[side].getSideID();
							side = cube.getSides()[side].getDown();
						}
						
 						cube.rotate(s, btnCol, 0);//rotate the row selected
 						colorBtns(btns, cube);//change the colors to match the side that is showing in the cube
					}
					else if(me.getSceneY()<locy&&me.getSceneY()-locy<50){
						int[] s = new int[4];
						
						int side = cube.getCurSide();//use to track the turn
						for(int k=0; k<4; k++){//add the sides to be rotated to the list
							s[k] = cube.getSides()[side].getSideID();
							side = cube.getSides()[side].getUp();
						}
						
 						cube.rotate(s, btnCol, 0);//rotate the row selected
 						colorBtns(btns, cube);//change the colors to match the side that is showing in the cube
					}
					if(cube.isSolved()){//the player has finished the game
						long end = System.currentTimeMillis();
						long time = end - start;
						Text t = new Text("Congrats! You solved it in " + time + " seconds");
						t.setLayoutX(100);
						t.setLayoutY(400);
						for(int k=0; k<3; k++){//disable all the game buttons since the game is over
							for(int m=0; m<3; m++){
								btns[k][m].setDisable(true);
							}
						}
						pane.getChildren().add(t);
					}
				});
				
				//initialize starting colors and add to the pane
				pane.getChildren().add(btns[i][j]);
			}
		}
		colorBtns(btns, cube);
		
		//displays which side is currently showing
		Text cSide = new Text("Current side: " + cube.getCurSide());
		cSide.setLayoutX(400);
		cSide.setLayoutY(50);
		pane.getChildren().add(cSide);
		
		//buttons for checking turning to look at different sides of the cube
		Button tUp = new Button("Turn to side " + cube.getSides()[cube.getCurSide()].getUp());
		Button tDown = new Button("Turn to side " + cube.getSides()[cube.getCurSide()].getDown());
		Button tLeft = new Button("Turn to side " + cube.getSides()[cube.getCurSide()].getLeft());
		Button tRight = new Button("Turn to side " + cube.getSides()[cube.getCurSide()].getRight());
		tUp.setLayoutX(175);
		tUp.setLayoutY(125);
		tDown.setLayoutX(175);
		tDown.setLayoutY(325);
		tLeft.setLayoutX(25);
		tLeft.setLayoutY(225);
		tRight.setLayoutX(325);
		tRight.setLayoutY(225);
		tUp.setOnMouseClicked(me->{
			cube.setCurSide(cube.getSides()[cube.getCurSide()].getUp());
			tUp.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getUp());
			tDown.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getDown());
			tLeft.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getLeft());
			tRight.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getRight());
			cSide.setText("Current side: " + cube .getCurSide());
			colorBtns(btns, cube);
		});
		tDown.setOnMouseClicked(me->{
			cube.setCurSide(cube.getSides()[cube.getCurSide()].getDown());
			tUp.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getUp());
			tDown.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getDown());
			tLeft.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getLeft());
			tRight.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getRight());
			cSide.setText("Current side: " + cube .getCurSide());
			colorBtns(btns, cube);
		});
		tLeft.setOnMouseClicked(me->{
			cube.setCurSide(cube.getSides()[cube.getCurSide()].getLeft());
			tUp.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getUp());
			tDown.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getDown());
			tLeft.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getLeft());
			tRight.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getRight());
			cSide.setText("Current side: " + cube .getCurSide());
			colorBtns(btns, cube);
		});
		tRight.setOnMouseClicked(me->{
			cube.setCurSide(cube.getSides()[cube.getCurSide()].getRight());
			tUp.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getUp());
			tDown.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getDown());
			tLeft.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getLeft());
			tRight.setText("Turn to side " + cube.getSides()[cube.getCurSide()].getRight());
			cSide.setText("Current side: " + cube .getCurSide());
			colorBtns(btns, cube);
		});
		pane.getChildren().addAll(tUp,tDown,tLeft,tRight);
		
		Text howTo = new Text("Click and drag the row or column in the direction you wish to rotate");
		howTo.setLayoutX(70);
		howTo.setLayoutY(20);
		pane.getChildren().add(howTo);
		
		//setup the stage and show it
		Scene scene = new Scene(pane);
        stage.setScene(scene); 
        stage.setHeight(500);
        stage.setWidth(500);
        start = System.currentTimeMillis();
        stage.show();
	}

	public void colorBtns(Button[][] btns, Cube cube){//display the current side's colors
		for(int k=0; k<3; k++){
				for(int m=0; m<3; m++){
					if(cube.getSides()[cube.getCurSide()].getColors()[k][m]==0){
						btns[k][m].setStyle("-fx-color: white;");
					}
					if(cube.getSides()[cube.getCurSide()].getColors()[k][m]==1){
						btns[k][m].setStyle("-fx-color: blue;");
					}
					if(cube.getSides()[cube.getCurSide()].getColors()[k][m]==2){
						btns[k][m].setStyle("-fx-color: orange;");
					}
					if(cube.getSides()[cube.getCurSide()].getColors()[k][m]==3){
						btns[k][m].setStyle("-fx-color: green;");
					}
					if(cube.getSides()[cube.getCurSide()].getColors()[k][m]==4){
						btns[k][m].setStyle("-fx-color: red;");
					}
					if(cube.getSides()[cube.getCurSide()].getColors()[k][m]==5){
						btns[k][m].setStyle("-fx-color: yellow;");
					}
				}
			}
	}
	
}
