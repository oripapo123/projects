using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataBase;

namespace ServerBL
{
    public class LoggedInUser
    {
        private MyDataBaseDataContext db = new MyDataBaseDataContext();
        private Client c = null;
        private System_Manager SM = null;
        private Business_Owner BO = null;
        private int userType; //0 for client, 1 for buisness owner, 2 for system manager
        public LoggedInUser(Client cl)
        {
            c = cl;
            userType = 0;
        }

        public LoggedInUser(System_Manager sm)
        {
            SM = sm;
            userType = 2;
        }

        public LoggedInUser(Business_Owner bo)
        {
            BO = bo;
            userType = 1;
        }



        public int getUserType()
        {
            return userType;
        }

        public Boolean changePassword(String password)
        {
            Boolean ret = true;
            try
            {
                switch (userType)
                {
                    case 0:
                        foreach (Client cl in db.Clients)
                        {
                            if (String.Compare(cl.username, c.username) == 0)
                            {
                                c.password = password;
                                cl.password = password;
                                db.SubmitChanges();
                            }
                        }
                        break;
                    case 1:
                        foreach (Business_Owner business_owner in db.Business_Owners)
                        {
                            if (String.Compare(business_owner.username, BO.username) == 0)
                            {
                                BO.password = password;
                                business_owner.password = password;
                                db.SubmitChanges();
                            }
                        }
                        break;

                    case 2:
                        foreach (System_Manager system_manager in db.System_Managers)
                        {
                            if (String.Compare(system_manager.username, SM.username) == 0)
                            {
                                SM.password = password;
                                system_manager.password = password;
                                db.SubmitChanges();
                            }
                        }
                        break;
                }
            }

            catch
            {
                ret = false;
            }
            return ret;
        }

        public String getName()
        {
            String retName = "";
            switch (userType)
            {
                case 0:
                    retName = c.first_name;
                    break;
                case 1:
                    retName = BO.first_name;
                    break;
                case 2:
                    retName = SM.first_name;
                    break;
            }
            return retName;
        }

        public String getPassword()
        {
            String retPW = "";
            switch (userType)
            {
                case 0:
                    retPW = c.password;
                    break;
                case 1:
                    retPW = BO.password;
                    break;
                case 2:
                    retPW = SM.password;
                    break;
            }
            return retPW;
        }

        public String getUserName()
        {
            String retName = "";
            switch (userType)
            {
                case 0:
                    retName = c.username;
                    break;
                case 1:
                    retName = BO.username;
                    break;
                case 2:
                    retName = SM.username;
                    break;
            }
            return retName;
        }

    }
}
