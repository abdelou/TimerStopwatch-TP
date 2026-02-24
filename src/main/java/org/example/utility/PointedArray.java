package org.example.utility;

public class PointedArray<E> {
	
	private E[] array;
	private int pointer=0;
	
	public PointedArray(E[] tab) {
		array=tab; }
	
	public void setPointer(int i) throws IndexOutOfBoundsException {
		if (i<0 || i>=array.length) {
			throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for the PointedArray"); }
		else pointer=i; }
	
	public int getPointer() {
		return pointer; }
		
	public E getPointedItem() {
		return array[pointer]; }
	
	public int getSize() { 
		return array.length; }
		
	public E get(int i) throws IndexOutOfBoundsException {
		if (i<0 || i>=array.length) {
			throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for the PointedArray"); }
		else return array[i]; }
		
	public void changePointer() {
		pointer=(pointer+1)%array.length; } }
