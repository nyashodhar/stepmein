/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Spoton.Server.Responses;

import Spoton.Server.Database.UserTable;
import Spoton.Server.Impl.CheckIn;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yash.Narvaneni
 */
public class UserCheckIn_BusinessTransfer 
{
private String m_userName;
private String m_phoneNumber;
private int m_age;
private Date m_DOB;
private String m_address;
private String m_city;
private String m_zip;
private String m_state;
private int m_partySize;
private Date m_checkINTime; 
private Date m_ReservationTime;

public void SetUserName(String userName)
    {
    m_userName = userName;
    }

public void SetPhoneNumber(String phoneNumber)
    {
    m_phoneNumber = phoneNumber;
    }

public void SetAge(int Age)
    {
    m_age = Age;
    }

public void SetDOB(Date DOB)
    {
    m_DOB = DOB;
    }

public void SetAddress(String address)
    {
    m_address = address;
    }

public void SetCity (String city)
    {
    m_city = city;
    }

public void SetState(String state)
    {
    m_state = state;
    }

public void SetPartySize(int partySize)
    {
    m_partySize = partySize;
    }

public void SetCheckInTime(Date checkInTime)
    {
    m_checkINTime = checkInTime;
    }

public void SetReservationTime(Date resvTime)
    {
    m_ReservationTime = resvTime;
    }
public UserCheckIn_BusinessTransfer(ResultSet rs, Date currentDateTime, Date timeofReservation, int numberOfPeople)
    {
        try
            {
            rs.next();    
            this.m_userName = rs.getString(UserTable.FIRST_NAME) + " " + 
                    rs.getString(UserTable.LAST_NAME);
            this.m_phoneNumber = rs.getString(UserTable.PHONE_NUMBER);
            if(rs.getDate(UserTable.DATE_OF_BIRTH) != null)
                this.m_age = (int) ((new java.util.Date().getTime() - rs.getDate(UserTable.DATE_OF_BIRTH).getTime()) / CheckIn.MILLISECONDS_IN_DAY) / 365;
            else
                this.m_age = 0;
            this.m_DOB = rs.getDate(UserTable.DATE_OF_BIRTH);
            this.m_address = rs.getString(UserTable.ADDRESS_1) + " "
                    + rs.getString(UserTable.ADDRESS_2);
            this.m_city = rs.getString(UserTable.CITY);
            this.m_state = rs.getString(UserTable.STATE);
            this.m_zip = rs.getString(UserTable.ZIP);
            this.m_partySize = numberOfPeople;
            this.m_checkINTime = currentDateTime;
            this.m_ReservationTime = timeofReservation;
            }
        catch(SQLException e)
            {
            
            }
    }

public UserCheckIn_BusinessTransfer()
    {
    }

}
