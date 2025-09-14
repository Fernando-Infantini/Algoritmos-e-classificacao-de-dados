public class Main{

    public static void main(String args[]){

        Serial ser = new Serial();
		Parallel par = new Parallel();

		ser.addResistor(new Resistor(300));
		ser.addResistor(new Resistor(500));
		ser.addResistor(new Resistor(1200));

		par.addResistor(new Resistor(50));
		par.addResistor(new Resistor(100));
		par.addResistor(new Resistor(300));

		System.out.printf("\nExemplos enunciado:\nSerie = %.2f\nParallel = %.2f\n\n", ser.getResistance(), par.getResistance());

		// Série interna do paralelo A
  	  Serial sa1 = new Serial();
    	sa1.addResistor(new Resistor(100));
    	sa1.addResistor(new Resistor(200)); // 300 

    	Serial sa2 = new Serial();
    	sa2.addResistor(new Resistor(400));
    	sa2.addResistor(new Resistor(600)); // 1000 

    	Parallel pA = new Parallel();
    	pA.addResistor(sa1);
    	pA.addResistor(sa2); // 230.77 

    	// Série interna do paralelo B
    	Serial sb1 = new Serial();
    	sb1.addResistor(new Resistor(150));
    	sb1.addResistor(new Resistor(250)); // 400 

    	Serial sb2 = new Serial();
    	sb2.addResistor(new Resistor(500));
    	sb2.addResistor(new Resistor(500)); // 1000 

    	Parallel pB = new Parallel();
    	pB.addResistor(sb1);
    	pB.addResistor(sb2); // 285.71 

    	// Série principal (paralelo A + paralelo B)
    	Serial principal = new Serial();
    	principal.addResistor(pA);
    	principal.addResistor(pB);

    	System.out.printf("Circuito em série formado por dois blocos paralelos:\nParalelo A: (100 + 200) || (400 + 600)\nParalelo B: (150 + 250) || (500 + 500)\nResistência equivalente = %.2f\n",principal.getResistance());




    }
    
}