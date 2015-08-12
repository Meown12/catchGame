package misc;

import java.io.Serializable;

public class KeyInfo implements Serializable
{
	private final int key;
	private final boolean pressed;

	public KeyInfo(int key, boolean pressed)
	{
		this.key = key;
		this.pressed = pressed;
	}

	public int getKey() { return key; }
	public boolean isPressed() { return pressed; }
}
