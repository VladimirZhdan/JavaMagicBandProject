package main.com.bsuir.autoservice.service;

import main.com.bsuir.autoservice.bean.notification;
import main.com.bsuir.autoservice.service.crud.exception.ServiceException;

import java.util.List;

public interface INotificationService extends IService {
    boolean haveNewNotification() throws ServiceException;
    List<notification> getOrderNotifications(int userId, int orderId, int notificationGroup, int notificationNumber)
            throws ServiceException;
}
