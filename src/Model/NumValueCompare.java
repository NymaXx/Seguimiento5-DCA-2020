package Model;

import java.util.Comparator;

public class NumValueCompare implements Comparator<NumsTo>{

	@Override
	public int compare(NumsTo o1, NumsTo o2) {
		// TODO Auto-generated method stub
		return o1.getValue()-o2.getValue();
	}

}
