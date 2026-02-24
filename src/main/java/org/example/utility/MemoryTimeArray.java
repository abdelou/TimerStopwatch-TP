package org.example.utility;

public class MemoryTimeArray {
	
	private Time[] array;
	
	public MemoryTimeArray(int n) throws IllegalArgumentException {
		if (n<2) {
			throw new IllegalArgumentException("Argument must be at least 2"); }
		else {
			array = new Time[n]; } }
	
	public int getSize() {
		return array.length; }
		
	public void add(Time myTime) {
		int index = 0;
		while (array[index]!=null && index!=array.length-1) {
			index++; }
		if (array[index]==null) {
			array[index] = myTime; }
		else {
			for (int i=0; i<array.length-1; i++) {
				array[i] = array[i+1]; }
			array[array.length-1] = myTime; } }
	
	public Time get(int i) throws IndexOutOfBoundsException {
		if (i>=array.length) {
			throw new IndexOutOfBoundsException("Index max is "+(array.length-1)); }
		else {
			return array[i] ; } } 
			
	public void clear() {
		array = new Time[array.length]; } }
