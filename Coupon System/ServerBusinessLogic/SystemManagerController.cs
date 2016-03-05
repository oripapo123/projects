using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataBase;

namespace ServerBusinessLogic
{
    public class SystemManagerController
    {
        static SystemManagerController instance = null;
        static MyDataBaseDataContext db = new MyDataBaseDataContext();


        public static SystemManagerController getInstance() {
            if (instance == null)
            {
                instance = new SystemManagerController();
            }
            return instance;
        }

        public bool addBusiness(Business toAdd)
        {
            if (TryAddNewBusiness(toAdd.business_name))
            {
                db.Businesses.InsertOnSubmit(toAdd);
                db.SubmitChanges();
                return true;
            }
            return false;
        }

        public void addBusinessOwner(Business_Owner toAdd)
        {
            db.Business_Owners.InsertOnSubmit(toAdd);
            db.SubmitChanges();
        }

        public bool ApproveCoupon(Coupon coupon)
        {
            try
            {
                foreach (Coupon c in db.Coupons)
                {
                    if (c.Serial_Number == coupon.Serial_Number)
                    {
                        c.isApproved = true;
                        db.SubmitChanges();
                    }
                }
                return true;
            }
            catch
            {
                return false;
            }
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

        public LoggedInUser TryLogin(String username, String password)
        {
            LoggedInUser ret = null;
            
                foreach (System_Manager SM in db.System_Managers)
                {
                    if ((String.Equals((SM.username).Trim(), username)) && (String.Equals((SM.password).Trim(), password)))
                    {
                        ret = new LoggedInUser(SM);
                    }
                }
            return ret;
        }

        public bool TryAddNewBusiness(String BusinessName)
        {
            Boolean exist = false;
            foreach (Business b in db.Businesses)
            {
                if ((!exist) && ((String.Compare((b.business_name).Trim(), BusinessName) == 0)))
                {
                    exist = true;
                }
            }
            return exist;
        }


        public bool TryRemove(Business business)
        {
            Boolean okay = false;
            try
            {
                foreach (Business b in db.Businesses)
                {
                    if (String.Compare(b.business_name, business.business_name) == 0)
                        db.Businesses.DeleteOnSubmit(b);
                }
                db.SubmitChanges();
                okay = true;
            }
            catch
            {
                okay = false;
            }
            return okay;
        }

        public List<Coupon> generateUnApprovedCoupons()
        {

            var q = (from c in db.Coupons
                     where c.isApproved == false
                     select c).ToList();

            return q;
        }

        public List<Business> generateBusinesses()
        {

            var q = (from business in db.Businesses
                     select business).ToList();

            return q;
        }

        public bool TryEdit(Business business)
        {
            Boolean okay = false;
                try
                {
                    foreach (Business b in db.Businesses)
                    {
                        if (String.Compare(b.business_name, business.business_name) == 0)
                        {
                            b.address = business.address;
                            b.city = business.city;
                            b.description = business.description;
                            b.category = business.category;
                        }
                    }
                    db.SubmitChanges();
                    okay = true;
                }
                catch
                {
                    okay = false;
                }
            return okay;
        }

    }
}
