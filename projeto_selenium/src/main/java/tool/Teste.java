package tool;

public class Teste {

	
	public static void main(String[] args) {
		
		int[] one = {1,3,4,5,7};
		int[] two = {2,6};
		
		merge(one,two);
		
	}

	private static void merge(int[] one, int[] two) {
		int[] merge = new int[one.length + two.length];
		int i_one = 0;
		int j_two = 0;
		int k = 0;
		while (i_one < one.length && j_two < two.length) {
			
				if(one[i_one] < two[j_two]){
					merge[k++] = one[i_one++];
				}else{
					merge[k++] = two[j_two++];
				}
		}
		
		while (i_one < one.length) {
			merge[k++] = one[i_one++];
		}
		while (j_two < two.length) {
			merge[k++] = two[j_two++];
		}
		
		for (int i : merge) {
			System.out.print(i + ",");
		}
	}
	
}
