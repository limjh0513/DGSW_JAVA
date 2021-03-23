package kr.hs.dgsw.javaPrograming.w20bject;

public class AccumulatorRunner {

	public static void main(String[] args) {
		Accumulator acuumAccumulator = new Accumulator();
		
		acuumAccumulator.printResult();
		
		acuumAccumulator.add(8);
		acuumAccumulator.add(7);
		acuumAccumulator.add(-2);
		acuumAccumulator.printResult();
		
		System.out.println("RESULT : " + acuumAccumulator.getSum());
	}

}
