package src;

import java.util.ArrayList;

class Group {
	private ArrayList<Person> array;

	public Group(Person persons[], int lowerBound, int upperBound) {
		//NOTE, lowerBound is inclusive but upperBound is inclusive

		array = new ArrayList<Person>();
		array.ensureCapacity(upperBound - lowerBound);

		for(int i = lowerBound; i < upperBound; i++) {
			array.add(persons[i]);
		}
	}

	public Group(int initialCapacity) {
		array = new ArrayList<Person>();
		array.ensureCapacity(initialCapacity);
	}

	public Person[] getInternalArray() {
		Person newArray[] = new Person[array.size()];
		return array.toArray(newArray);
	}

	public boolean testGroup() {
		boolean positive = false;
		for(Person person : array) {
			if(person.getState()) { // Tests positive
				positive = true;
				return positive;
			}
		}
		for(Person person : array) {
			person.clear();
		}
		return positive;
	}

	public void addMember(Person person) {
		array.add(person);
	}

	public String toString() {
		String rtn = "";

		rtn += "Size: " + array.size() + " | ";

		for(Person person : array) {
			rtn += person.getState() + " ";
		}

		rtn += "| Test: " + testGroup();

		return rtn;
	}

	public int getSize() {
		return array.size();
	}




}
