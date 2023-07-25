package gr.aueb.cf.schoolapp.viewcontroller;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JFrame {
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private IUserService userService;
	private AdminMenu adminMenu;
	private AdminInsertCitiesForm adminInsertCitiesForm;
	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

	public Login() {
		IUserDAO userDAO = new UserDAOImpl();
		userService = new UserServiceImpl(userDAO);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/eduv2.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 241, 241));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/resources/school.jpg")));
		lblNewLabel.setBounds(0, 215, 437, 48);
		contentPane.add(lblNewLabel);

		JPanel login = new JPanel();
		login.setBounds(0, 11, 437, 199);
		contentPane.add(login);
		login.setLayout(null);

		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLbl.setBounds(93, 68, 71, 23);
		login.add(passwordLbl);

		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLbl.setBounds(93, 35, 71, 23);
		login.add(usernameLbl);

		usernameTxt = new JTextField();
		usernameTxt.setBounds(174, 35, 153, 23);
		login.add(usernameTxt);
		usernameTxt.setColumns(10);

		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTxt.getText().trim();
				String password = String.valueOf(passwordTxt.getPassword()).trim();

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter username and password.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				boolean loginSuccessful = checkLoginCredentials(username, password);

				if (loginSuccessful) {
					JOptionPane.showMessageDialog(null, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
					openAdminMenu();
					Login.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Login failed. Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginBtn.setForeground(new Color(0, 0, 255));
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginBtn.setBounds(238, 102, 90, 40);
		login.add(loginBtn);

		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(174, 68, 153, 23);
		login.add(passwordTxt);

		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTxt.getText().trim();
				String password = String.valueOf(passwordTxt.getPassword()).trim();

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter username and password.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				UserInsertDTO userDTO = new UserInsertDTO();
				userDTO.setUsername(username);
				userDTO.setPassword(password);

				boolean registrationSuccessful = registerUser(userDTO);

				if (registrationSuccessful) {
					JOptionPane.showMessageDialog(null, "Registration successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		registerBtn.setForeground(Color.BLUE);
		registerBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		registerBtn.setBounds(126, 102, 90, 40);
		login.add(registerBtn);

		setVisible(true);
	}
	private boolean checkLoginCredentials(String username, String password) {
		// Check if the password matches the regex pattern
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "Password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			List<User> users = userService.getUsersByUsername(username);

			for (User user : users) {
				if (user.getPassword().equals(password)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	private boolean registerUser(UserInsertDTO userDTO) {
		try {
			// Check if the username already exists in the database
			List<User> users = userService.getUsersByUsername(userDTO.getUsername());
			if (!users.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different username.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			userService.insertUser(userDTO);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	private void openAdminMenu() {
		if (adminMenu == null) {
			adminMenu = new AdminMenu();
			adminMenu.setVisible(true);
		} else {
			adminMenu.setEnabled(true);
		}

		if (adminInsertCitiesForm != null) {
			adminInsertCitiesForm.setVisible(false);
		}
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Login();
			}
		});
	}
}
