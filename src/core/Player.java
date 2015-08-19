package core;

import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.awt.Color;
import java.net.InetAddress;

public class Player implements Serializable
{
	public static final int SPEED = 5, SIZE = 20;
	private byte[] keys = new byte[200];
	private boolean running = false;
	private int x, y;
	private InetAddress address;

	public Player(int x, int y, boolean running, String addressString)
	{
		this.x = x;
		this.y = y;
		this.running = running;
		try
		{
			this.address = InetAddress.getByName(addressString);
		} catch (Exception e) { System.out.println("Player> set address: " + e); System.exit(1); }
	}

	public void tick()
	{
		// move
		x += (keys[KeyEvent.VK_RIGHT]-keys[KeyEvent.VK_LEFT])*SPEED;
		y += (keys[KeyEvent.VK_DOWN]-keys[KeyEvent.VK_UP])*SPEED;
	}

	public void applyKeys(byte[] keys)
	{
		for (int i = 0; i < keys.length; i++)
			this.keys[i] = keys[i];
	}

	public void move(int xOffset, int yOffset)
	{
		x += xOffset;
		y += yOffset;
	}

	public void render()
	{
		if (running)
		{
			Screen.g().setColor(Color.GREEN);
			Screen.g().fillRect(x, y, SIZE, SIZE);
		}
		else
		{
			Screen.g().setColor(Color.RED);
			Screen.g().fillRect(x, y, SIZE, SIZE);
		}
	}

	public boolean collide(Player player)
	{
		if (player == this)
			return false;

		if (Math.abs(x-player.x) < SIZE && Math.abs(y-player.y) < SIZE)
			return true;

		return false;
	}

	public void setRunning(boolean running) { this.running = running; }
	public void setPosition(int x, int y) { this.x = x; this.y = y; }

	public InetAddress getAddress() { return address; }
	public boolean isRunning() { return running; }
}
