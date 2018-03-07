package org.module.view;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.framework.util.ImagePanel;
import org.framework.util.MyFont;
import org.module.entity.User;
import org.module.services.UserService;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LoginJFrame extends JFrame implements MouseListener, FocusListener {

	// 全局的位置变量，用于表示鼠标在窗口上的位置
	static Point origin = new Point();

	// 定义全局组件
	JTextField username = new JTextField(20);
	JPasswordField password = new JPasswordField(20);
	ImagePanel backgroundPanel = null;
	JButton button_minimize, button_close, button_login, button_reset;

	public LoginJFrame() {
		
		Image backgrounImage = null;
		try {
			backgrounImage = ImageIO.read(new File("image/loginbackground.png"));
			Image imgae = ImageIO.read(new File("image/logo.png"));
			this.setIconImage(imgae);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 窗口背景面板
		backgroundPanel = new ImagePanel(backgrounImage);
		backgroundPanel.setLayout(null);

		username.setBounds(320, 222, 253, 53);
		username.setFont(MyFont.Static);
		username.addFocusListener(this);
		username.setText("Username");
		username.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		password.setBounds(320, 295, 253, 53);
		password.setFont(MyFont.Static);
		password.addFocusListener(this);
		password.setText("Password");
		password.setEchoChar('\0');
		password.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		button_login = new JButton("登录");
		button_login.setBounds(280, 375, 100, 40);
		button_login.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_login.setForeground(Color.white);
		button_login.setFont(MyFont.Static);
		button_login.addMouseListener(this);

		button_reset = new JButton("重置");
		button_reset.setBounds(425, 375, 100, 40);
		button_reset.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		button_reset.setForeground(Color.white);
		button_reset.setFont(MyFont.Static);
		button_reset.addMouseListener(this);

		backgroundPanel.add(username);
		backgroundPanel.add(password);
		backgroundPanel.add(button_login);
		backgroundPanel.add(button_reset);

		this.add(backgroundPanel);
		this.setTitle("  销售管理系统");
		this.setSize(830, 530);
		this.setVisible(true);
		this.requestFocus();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	// 鼠标点击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_login) {
			if ("Username".equals(username.getText())) {
				JOptionPane.showMessageDialog(null, "用户名不能为空");
			} else if ("Password".equals(password.getText())) {
				JOptionPane.showMessageDialog(null, "密码不能为空");
			} else {
				String params[] = { username.getText(), password.getText() };
				UserService userService = new UserService();
				try {
					User user = userService.selectOne(params);
					if (user == null) {
						JOptionPane.showMessageDialog(null, "用户名密码有误");
					} else {
						this.setVisible(false);
						new IndexJFrame(user);

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == button_reset) {
			username.setText("Username");
			password.setText("Password");
			password.setEchoChar('\0');
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// 聚焦事件
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals("Username")) {
				username.setText("");
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("Password")) {
				password.setText("");
				password.setEchoChar('*');
			}
		}

	}

	// 失焦事件
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals("")) {
				username.setText("Username");
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("")) {
				password.setText("Password");
				password.setEchoChar('\0');
			}
		}
	}

	
}