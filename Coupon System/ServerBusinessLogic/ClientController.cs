using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataBase;

namespace ServerBusinessLogic
{
    public class ClientController
    {

        static ClientController instance = null;
        static MyDataBaseDataContext db = new MyDataBaseDataContext();


        public static ClientController getInstance()
        {
            if (instance == null)
            {
                instance = new ClientController();
            }
            return instance;
        }

        public void addClient(Client toAdd)
        {
            db.Clients.InsertOnSubmit(toAdd);
            db.SubmitChanges();
        }

        public Boolean OrderCoupon(Coupon c, LoggedInUser user)
        {
            Boolean toRet = true;
            Coupons_of_Client coc = new Coupons_of_Client();
            coc.Client = user.getUserName();
            coc.Coupon_Serial_Number = c.Serial_Number;
            try
            {
                db.Coupons_of_Clients.InsertOnSubmit(coc);
                db.SubmitChanges();
            }
            catch
            {
                toRet = false;
            }
            return toRet;
        }

        public List<Coupon> SearchCoupons(String CatID, String Serial, String Rating, String minPrice, String maxPrice, String Category)
        {
            List<Coupon> toReturn = new List<Coupon>();

            foreach (Coupon c in db.Coupons)
                if (((String.Compare(CatID, "") == 0) || (c.categoryID == int.Parse(CatID))) &&
                       ((String.Compare(Serial, "") == 0) || (c.Serial_Number == int.Parse(Serial))) &&
                       ((String.Compare(Rating, "No Rating") == 0) || (c.rating == int.Parse(Rating))) &&
                       ((String.Compare(minPrice, "") == 0) || (c.discountedPrice >= int.Parse(minPrice))) &&
                       ((String.Compare(maxPrice, "") == 0) || (c.discountedPrice <= int.Parse(maxPrice))) &&
                       ((String.Compare(Category, "") == 0) || (c.couponCategory == int.Parse(Category))) &&
                        c.isApproved == true)
                    toReturn.Add(c);
            return toReturn;
        }

        public List<Business> SearchBusinesses(string City, string Cat, string Name)
        {
            List<Business> toReturn = new List<Business>();

            foreach (Business b in db.Businesses)
                if (((String.Compare(Cat, "") == 0) || (b.category == int.Parse(Cat))) &&
                       ((String.Compare(City, "") == 0) || (String.Compare(b.city, City)) == 0) &&
                       ((String.Compare(Name, "") == 0) || (String.Compare(b.business_name, Name)) == 0))
                    toReturn.Add(b);
            return toReturn;
        }

        public LoggedInUser TryLogin(String username, String password)
        {
            LoggedInUser user = null;
            foreach (Client c in db.Clients)
            {
                if ((String.Compare((c.username).Trim(), username) == 0) && (String.Compare((c.password).Trim(), password) == 0))
                {
                    user = new LoggedInUser(c);
                }
            }
            return user;
        }

        public List<Coupon> generateCoupons(LoggedInUser user)
        {
            var temp = (from coc in db.Coupons_of_Clients
                        where (String.Compare(coc.Client, user.getUserName()) == 0)
                        select coc).ToList();

            var q = (from ser in temp
                     join c in db.Coupons on ser.Coupon_Serial_Number equals c.Serial_Number
                     select c).ToList();

            return q;
        }

        public List<Business> generateBusinesses()
        {

            var q = (from business in db.Businesses
                     select business).ToList();

            return q;
        }

        public Boolean TryForgotPassword(String email)
        {
            Boolean ret = false;
            foreach (Client c in db.Clients)
            {
                if (c.email != null)
                {
                    if (String.Compare((c.email).Trim(), email) == 0)
                    {
                        ret = true;
                        //Send Actual Email
                    }
                }
            }
            if (ret == false)
            {
                foreach (Business_Owner BO in db.Business_Owners)
                {
                    if (BO.email != null)
                    {
                        if (String.Compare((BO.email).Trim(), email) == 0)
                        {
                            ret = true;
                            //Send Actual Email
                        }
                    }
                }
            }

            if (ret == false)
            {
                foreach (System_Manager SM in db.System_Managers)
                {
                    if (SM.email != null)
                    {
                        if (String.Compare((SM.email).Trim(), email) == 0)
                        {
                            ret = true;
                            //Send Actual Email
                        }
                    }
                }
            }
            return ret;
        }

        public Boolean TryAddNewUser(String username, String email)
        {
            Boolean exist = false;
            foreach (Client c in db.Clients)
            {
                if ((!exist) && ((String.Compare((c.username).Trim(), username) == 0) || (String.Compare((c.email).Trim(), email) == 0)))
                {
                    exist = true;
                }
            }
            if (exist == false)
            {
                foreach (Business_Owner BO in db.Business_Owners)
                {
                    if ((!exist) && ((String.Compare((BO.username).Trim(), username) == 0) || (String.Compare((BO.email).Trim(), email) == 0)))
                    {
                        exist = true;
                    }
                }
            }

            if (exist == false)
            {
                foreach (System_Manager SM in db.System_Managers)
                {
                    if ((!exist) && ((String.Compare((SM.username).Trim(), username) == 0) || (String.Compare((SM.email).Trim(), email) == 0)))
                    {
                        exist = true;
                    }
                }
            }

            return exist;
        }
    }
}
