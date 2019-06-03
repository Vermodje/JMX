package accountServer;

public interface AccountServerControllerMBean {
    void setUsersLimit(int usersLimit);
    int getUsersLimit();
}
