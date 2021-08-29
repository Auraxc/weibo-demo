// package web23.web18.service;
//
// import web23.web18.model.ServiceAuth;
// import web23.web18.model.User;
// import java.util.ArrayList;
//
// public class ServiceSession {
//     public static void insertSession(int userId, String session) {
//         String sql = "INSERT INTO session (user_id, session) VALUES (?, ?)";
//         MySQLStorage.update(sql, statement -> {
//             statement.setInt(1, userId);
//             statement.setString(2, session);
//         });
//     }
//     public static User userFromSessionId(String sessionId) {
//         String sql = "SELECT * FROM user join session ON user.id = session.user_id WHERE session.session = ?";
//         ArrayList<User> user = MySQLStorage.select(
//                 sql,
//                 resultSet -> {
//                     int id = resultSet.getInt("id");
//                     String username = resultSet.getString("username");
//                     String password = resultSet.getString("password");
//                     String userRole = resultSet.getString("user_role");
//                     String salt = resultSet.getString("salt");
//                     return new User(id, username, password, UserRole.valueOf(userRole), salt);
//                 },
//                 statement -> {
//                     statement.setString(1, sessionId);
//                 }
//         );
//
//         if (user.size() > 0) {
//             return user.get(0);
//         }
//         return ServiceAuth.guest();
//     }
// }
