package com.ecivil.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecivil.model.Location;
import com.ecivil.model.Role;
import com.ecivil.model.team.Team;
import com.ecivil.model.user.User;
import com.ecivil.repository.LocationDao;
import com.ecivil.repository.RoleDao;
import com.ecivil.repository.TeamDao;
import com.ecivil.repository.UserDao;
import com.ecivil.service.UserService;
import com.ecivil.util.ConstantUtil;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private MailSender mailSender;
	@Autowired
	private LocationDao locationDao;

	@Override
	@Transactional
	public User getUser(String login) throws DataAccessException {
		return userDao.getUser(login);
	}

	@Override
	@Transactional
	public void createUser(User user) throws DataAccessException {
		if (user.getRole() == null) {
			logger.debug("NO ROLE");
			/*
			 * Role role = roleDao.getDefaultRole(); logger.debug("MEMBER ROLE"
			 * + role.getRole()); user.setRole(role);
			 */
			user.setUuid(java.util.UUID.randomUUID().toString());
			String msg = "Dear "
					+ user.getFirstName()
					+ " "
					+ user.getLastName()
					+ ", thank you for becoming a memeber of ecivil. In order to confirm your email account please follow the link below   "
					+ "http://localhost:8080/ecivil/confirm/" + user.getUuid()
					+ "/email";
			try {
				sendMail("ecivilapp@gmail.com", user.getGoogleAccount(),
						"Confirm your email address for ecivil app", msg);
				logger.debug("MAIL SENT TO USER " + user.getLogin());
			} catch (MailException ex) {
				logger.debug("ERROR WHILE SENDING MAIL TO USER "
						+ user.getLogin());
			}
		}
		userDao.insertUser(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAllUsers() throws DataAccessException {
		return (List<User>) userDao.getAllUsers();
	}

	@Override
	@Transactional
	public User findUserById(int userId) throws DataAccessException {
		return userDao.findUserById(userId);
	}

	@Override
	@Transactional
	public void updateUser(User user) throws DataAccessException {
		if (user.getId() != null) {
			userDao.updateUser(user);
		}
	}

	@Override
	@Transactional
	public void deleteUser(int userId) throws DataAccessException {
		userDao.deleteUser(userId);
	}

	@Override
	@Transactional
	public void verifyUser(int userId, int teamId) throws DataAccessException {
		userDao.verifyUser(userId, teamId);
		User user = userDao.findUserById(userId);
		if (!user.getRole().getRole().contains("ADMIN")) {
			Team team = teamDao.findTeamById(teamId);
			if (team.getType().isInstitutional()) {
				user.setRole(roleDao.getRole(ConstantUtil.ROLE_INSTITUTION));
			} else {
				user.setRole(roleDao.getRole(ConstantUtil.ROLE_VOLUNTEER));
			}
			userDao.updateUser(user);
		}
	}

	@Override
	@Transactional
	public void addUserResponsibility(int userId, int teamId, String responsStr)
			throws DataAccessException {
		userDao.addUserResponsibility(userId, teamId, responsStr);
	}

	@Override
	@Transactional
	public void removeUserFromTeam(int userId, int teamId)
			throws DataAccessException {

		User user = userDao.findUserById(userId);

		if (user.getUserTeams().size() == 1) {
			// user is not member of other teams
			// we should change his role to ROLE_MEMBER
			user.setRole(roleDao.getRole(ConstantUtil.ROLE_MEMBER));
			userDao.updateUser(user);
		}

		userDao.removeUserFromTeam(userId, teamId);
	}

	@Override
	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String login = auth.getName();
		return getUser(login);
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	private void sendMail(String from, String to, String subject, String msg) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(msg);
		mailSender.send(simpleMailMessage);
	}

	@Override
	@Transactional
	public User getUserByUuid(String uuid) throws DataAccessException {
		return userDao.getUserByUuid(uuid);
	}

	@Override
	@Transactional
	public void saveUserLocation(String login, double lat, double lon)
			throws DataAccessException {
		User user = this.userDao.getUser(login);
		Location currentLoc = user.getCurrent_location();
		if(currentLoc == null) {
			currentLoc = new Location();
		}
		if(lat != 0d && lon != 0d) {
			currentLoc.setLatitude(lat);
			currentLoc.setLongitude(lon);
		}

		user.setCurrent_location(currentLoc);
		this.userDao.updateUser(user);
		
		/*	boolean save = true;
		Location userCurLoc = user.getCurrent_location();
		if (userCurLoc != null) {
			if (userCurLoc.getLatitude() != null
					&& userCurLoc.getLongitude() != null) {
				if (userCurLoc.getLatitude().doubleValue() == lat
						&& userCurLoc.getLongitude().doubleValue() == lon) {
					save = false;
				}
			}
		}
		if (save == true) {
			// save location only if has been changed
			Location oldLocation = this.locationDao.getLocationByLatLon(lat,
					lon);
			if (oldLocation == null) {
				// there is no location with this latitude and longitude in DB
				// we need to create new
				Location newLocation = new Location();
				newLocation.setLatitude(lat);
				newLocation.setLongitude(lon);
				this.locationDao.insertLocation(newLocation);
				user.setCurrent_location(newLocation);
			} else {
				// we assign a location that is already been created
				user.setCurrent_location(oldLocation);
			}
			this.userDao.updateUser(user);
		}*/
	}

}
