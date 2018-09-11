package assignment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import poly.manhnd.assignment.entities.Category;

public class Test {
	public static void main(String[] args) {
		List<Category> list = new ArrayList<>();
		list.add(new Category("Laptop"));
		list.add(new Category("Tivi"));
		list.add(new Category("Tủ lạnh"));
		list.add(new Category("Lò Vi Sóng"));
		list.add(new Category("Điều hòa"));
		list.add(new Category("Tablet"));
		list.add(new Category("Smartphone"));
		list.add(new Category("Milk"));
		list.add(new Category("Mouse"));
		list.add(new Category("Keyboard"));

		int total = list.size(); 
		int max = 3;
		int totalGroup = (int) Math.ceil(total*1.0/max);
		
		int[] indexes = new int[totalGroup];
		for(int i =0;i<indexes.length;i++){
			indexes[i] = i*max;
		}
		
		LinkedHashMap<Integer, List<Category>> rows = new LinkedHashMap<>();

		int row = 0;
		for (int k = 0; k < indexes.length; k++) {
			List<Category> sublist = new ArrayList<>();
			row++;
			int firstIndexOfGroups = indexes[k];
			int condition = max + firstIndexOfGroups;
			for (int i = firstIndexOfGroups; i < condition; i++) {
				try {
					sublist.add(list.get(i));
				} catch (Exception e) {
					break;
				}
			}
			rows.put(row, sublist);
		}
		
		for(Entry<Integer, List<Category>> e: rows.entrySet()) {
			List<Category> categories = e.getValue();
			for(Category c: categories) {
				System.out.println(c.getName());
			}
			System.out.println();
		}
		
	}
}
