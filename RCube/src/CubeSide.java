
public class CubeSide {

	private int[][] colors = new int[3][3];
	private int sideID;
	private int up;
	private int down;
	private int left;
	private int right;
	
	public CubeSide(int c){
		sideID = c;
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				colors[i][j] = c;
			}
		}
		if(c==0){//set turn directions for white side
			up = 1;
			down = 3;
			right = 4;
			left = 2;
		}
		if(c==1){//set turn directions for blue side
			up = 5;
			down = 0;
			right = 4;
			left = 2;
		}
		if(c==2){//set turn directions for orange side
			up = 1;
			down = 3;
			right = 0;
			left = 5;
		}
		if(c==3){//set turn directions for green side
			up = 0;
			down = 5;
			right = 4;
			left = 2;
		}
		if(c==4){//set turn directions for red side
			up = 1;
			down = 3;
			right = 5;
			left = 0;
		}
		if(c==5){//set turn directions for yellow side
			up = 3;
			down = 1;
			right = 2;
			left = 4;
		}
	}
	
	public int[][] getColors(){
		return colors;
	}
	
	public void setColors(int[][] cs){
		colors = cs;
	}
	
	public int getSideID(){
		return sideID;
	}
	
	public int getUp(){
		return up;
	}
	
	public int getDown(){
		return down;
	}
	
	public int getLeft(){
		return left;
	}
	
	public int getRight(){
		return right;
	}
	
}
