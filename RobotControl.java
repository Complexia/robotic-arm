import javax.swing.*;
class RobotControl
{
	private Robot r;
	public RobotControl(Robot r)
	{
		this.r = r;
	}

	public void control(int barHeights[], int blockHeights[])
	{

		scenario(barHeights,blockHeights);

	}


	public void scenario(int barHeights[], int blockHeights[])
	{
		//initial height, width, and depth of the arm.
		//robot methods:
		//r.up()
		//r.extend()
		//r.lower()
		//r.raise()
		//r.contract()
		//r.down() 
		//r.pick()
		//r.drop()
		int h = 2;
		int w = 1;
		int d = 0;
		int totalStack = 0;
		int sumOfAllBlocks = 0;
		for(int k = 0; k<blockHeights.length;k++) {
			sumOfAllBlocks += blockHeights[k];
		}

		int highestBar = 0;
		for(int k = 0; k<barHeights.length;k++) {
			if(barHeights[k] > highestBar) {
				highestBar = barHeights[k];
			}
		}

		for(int i=0;i<blockHeights.length;i++) {
			int currentBlock = blockHeights[blockHeights.length -i - 1]; //height of the topmost block
			int totalHeight = 0; //total height to be raised
			
			if(highestBar > sumOfAllBlocks) {
				totalHeight = highestBar;
			}
			else {
				totalHeight = sumOfAllBlocks;
			}
			while(h-d < totalHeight + 1) {
				if(d > 0) {
					r.raise();
					d--;
				}
				else {
					r.up();
					h++;
				}
			}
			while(w<10) {
				r.extend();
				w++;
			}
			while(h-d-1 > sumOfAllBlocks) {
				r.lower();
				d++;
					
			}		
			r.pick();
			while(h-d-currentBlock < highestBar+ 1) {
				if(d > 0) {
					r.raise();
					d--;
				}
				else {
					r.up();
					h++;
				}
			}
			while(w>1) {
				r.contract();
				w--;
			}
			while(h-d-currentBlock > totalStack + 1) {
				r.lower();
				d++;
			}
			r.drop();
			totalStack += currentBlock;
			sumOfAllBlocks -= currentBlock;

		}
	}
}
