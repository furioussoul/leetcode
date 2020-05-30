package nio.buffers;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;


public class BufferAccess {
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);
		printBuffer(buffer);

		buffer.put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'0');
		printBuffer(buffer);

		buffer.flip();
		printBuffer(buffer);

		//取buffer
		System.out.println("" + (char) buffer.get() + (char) buffer.get());
		printBuffer(buffer);

		//标记当前position
		buffer.mark();
		printBuffer(buffer);

		//读取两个元素后，恢复到之前mark的位置处
		System.out.println("" + (char) buffer.get() + (char) buffer.get());
		printBuffer(buffer);

		//将指针移动到标记处，用于重读
		buffer.reset();

		//将指针移动到0，清除mark标记
//		buffer.rewind();

		printBuffer(buffer);

		//删除已读的字节
		buffer.compact();
		printBuffer(buffer);
		
		//清除所有标记，limit=0
		buffer.clear();
		printBuffer(buffer);

	}
	
	private static void printBuffer(Buffer buffer) {
		System.out.println("[limit=" + buffer.limit() 
				+", position = " + buffer.position()
				+", capacity = " + buffer.capacity()
 				+", array = " + buffer.toString()+"]");
	}
}
