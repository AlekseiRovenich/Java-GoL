import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dibujo extends JPanel implements ActionListener{
	private int width, height; // dimensiones
	private int x, y, resolution;
	private int xSpeed;
	private Timer t;
	private int ms;
	private int[][] population;
	
	public Dibujo(){
		//super("Dibujitos c:");
		this.width = 900;
		this.height = 900;
		this.x = 100;
		this.y = 100;
		this.xSpeed = 5;
		this.ms = 100;
		this.resolution = 10;
		this.population = setInitialSeed();
		// setSize(400, 400);
		// setVisible(true);

	}


	public void paintComponent(Graphics g){
		super.paintComponent(g); // llamamos al metodo paint
		
		// draw backgrond
		drawBackground(g);

		// draw white spaces 
		drawPopulation(g, this.population);

		// calculate new population
		calcPopulation();


	}

	public void calcPopulation(){
		/* REGLAS
		 *  any live cell with fewer thatn two live neighbours dies, as if by underpopulation
		 *  any live cell with two or three live neighbours lives on to the next generation
		 *  any live cell with more than three live neighbours dies, as if by overpopulation
		 *  any dead cell with exactly three live neighbours become a live cell, as if by reproduction
		 */

		//int[][] population = new int[width/resolution][height/resolution];
		// population = this.population;
		int neigh = 0;
		// this.population is the previous population and population is going to be de new population
		
		for (int row = 1; row <= this.population[0].length; row++){
			for (int col = 1; col <= this.population[0].length; col++){
				// population[row][col] = (int)(Math.round(Math.random()));
				// System.out.println("row: " + row + " col: " + col);


				if (this.population[row % this.population[0].length][col % this.population[0].length] == 0){ // celula muerta
					// checamos arriba
					for (int vec = -1; vec <= 1; vec++){
						if (this.population[(row-1) % this.population[0].length][(col-vec) % this.population[0].length] == 1){
							//System.out.println("entra al for de arriba");
							neigh++;
						}		
					}	
					// checamos en medio
					for (int vec = -1; vec <= 1; vec++){
						if (this.population[row % this.population[0].length][(col-vec) % this.population[0].length] == 1){
							//System.out.println("entra al for de en medio");
							neigh++;
						}		
					}
					// checamos abajo
					for (int vec = -1; vec <= 1; vec++){
						if (this.population[(row+1) % this.population[0].length][(col-vec) % this.population[0].length] == 1){
							//System.out.println("entra al for de abajo");
							neigh++;
						}		
					}

					if (neigh == 3){
						population[row % this.population[0].length][col % this.population[0].length] = 1;
					} else {
						population[row % this.population[0].length][col % this.population[0].length] = 0;
					}
				
				} else {
					// checamos arriba
					for (int vec = -1; vec <= 1; vec++){
						if (this.population[(row-1) % this.population[0].length][(col-vec) % this.population[0].length] == 1){
							//System.out.println("entra al for de arriba");
							neigh++;
						}		
					}	
					// checamos en medio
					for (int vec = -1; vec <= 1; vec++){
						if (this.population[row % this.population[0].length][(col-vec) % this.population[0].length] == 1){
							//System.out.println("entra al for de en medio");
							neigh++;
						}		
					}
					// checamos abajo
					for (int vec = -1; vec <= 1; vec++){
						if (this.population[(row+1) % this.population[0].length][(col-vec) % this.population[0].length] == 1){
							//System.out.println("entra al for de abajo");
							neigh++;
						}		
					}

					if (neigh == 3){
						population[row % this.population[0].length][col % this.population[0].length] = 1;
					} else {
						population[row % this.population[0].length][col % this.population[0].length] = 0;
					}
				}	
			neigh = 0;

			
		}
	}

		this.population = population;
}

	public int[][] setInitialSeed(){
		int[][] seed = new int[width/resolution][height/resolution];
		for (int i = 0; i < seed[0].length; i++){
			for (int j = 0; j < seed[0].length; j++){
				seed[i][j] = (int)(Math.round(Math.random()));
			}
		}
		return seed;
	}

	public void drawPopulation(Graphics g, int[][] population){
		// cuadros blancos
		g.setColor(new Color(0, 0, 0));

		// recorremos el arreglo para saber cuales son los cuadros blancos
		for (int row = 0; row < population[0].length; row++){
			for (int col = 0; col < population[0].length; col++){
				if (population[row][col] == 0){
					g.fillRect(row*10, col*10, 10, 10);
				}
			}
		}
	}

	public void drawBackground(Graphics g){
		// super.paintComponent(g);
		// fondo negro
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, this.width, this.height);
		
		// lineas blancas
		g.setColor(new Color(255, 255, 255));
		for (int row = 0; row <= height; row += resolution){ // horizontal
			g.drawLine(0, row, width, row);
		}

		for (int col = 0; col <= width; col += resolution){
			g.drawLine(col, 0, col, height);  
		}

		

	}

	public void actionPerformed( ActionEvent evento){
		repaint();
		getToolkit().sync();
	}

	public void initialAnimation(){
		if (t == null){
			t = new Timer(this.ms, this);
			t.start();
		}
		
	       	else if(!t.isRunning()){
			t.restart();
		}
	}

	public Dimension getMinimumSize(){
		return new Dimension (width, height);
	//	return getPreferredSize();	
	}

	public Dimension getPreferredSize(){
		return new Dimension (width, height);
	}
}
