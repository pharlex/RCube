import java.util.Random;

public class Cube {

	//0 = white, 1=blue, 2=orange, 3=green, 4=red, 5=yellow
	private CubeSide[] sides = new CubeSide[6];
	private int curSide = 0;//the current side you are looking at
	
	public Cube(){
		for(int i=0; i<6; i++){
			sides[i] = new CubeSide(i);
		}
	}
	
	public void rotate(int[] s, int cr, int cORr){//a list of sides that are being rotated in order of rotation, cr is which column or row to rotate, cORr tells if its a column or row being rotated 0=column 1=row
		//function twists the row/column that is selected on the cube
		if(cORr==1){//rotating row
			int[][] temp = new int[4][3];
			for(int i=0; i<s.length; i++){
				for(int j=0; j<3; j++){
					temp[i][j] = sides[s[i]].getColors()[j][cr];
				}
			}
			for(int i=0; i<s.length; i++){
				for(int j=0; j<3; j++){
					if(i==0){
						sides[s[i]].getColors()[j][cr] = temp[3][j];
					}
					else{
						sides[s[i]].getColors()[j][cr] = temp[i-1][j];
					}
				}
			}
		}
		else{//rotating column
			int[][] temp = new int[4][3];
			for(int i=0; i<s.length; i++){
					temp[i] = sides[s[i]].getColors()[cr];
			}
			for(int i=0; i<s.length; i++){
				if(i==0){
					sides[s[i]].getColors()[cr] = temp[3];
				}
				else{
					sides[s[i]].getColors()[cr] = temp[i-1];
				}
			}
		}
		
	}
	
	public void mixUp(){//mixes up the cube for the player to solve
		Random rand = new Random();
		int[] s = new int[4];
		for(int i=0; i<2500; i++){
			int r = rand.nextInt(4);
			int side = 0;//use to track the turn
			for(int k=0; k<4; k++){//add the sides to be rotated to the list
				if(r==0){
					s[k] = sides[side].getSideID();
					side = sides[side].getUp();
				}
				else if(r==1){
					s[k] = sides[side].getSideID();
					side = sides[side].getLeft();
				}
				else if(r==2){
					s[k] = sides[side].getSideID();
					side = sides[side].getDown();
				}
				else if(r==3){
					s[k] = sides[side].getSideID();
					side = sides[side].getRight();
				}
			}
			rotate(s, rand.nextInt(3), r%2);
		}
	}
	
	public boolean isSolved(){
		for(int i=0; i<6; i++){
			int sColor = sides[i].getColors()[0][0];//get the first color of the side and compare it to the other sides
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					if(sides[i].getColors()[j][k]!=sColor){//if one of the colors is not the same return false
						return false;
					}
				}
			}
		}
		return true;//if all sides come back as having the same colors return true
	}
	
	public CubeSide[] getSides(){
		return sides;
	}
	
	public int getCurSide(){
		return curSide;
	}
	
	public void setCurSide(int s){
		curSide = s;
	}
	
}
