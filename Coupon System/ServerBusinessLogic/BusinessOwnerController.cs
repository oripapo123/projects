using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataBase;

namespace ServerBusinessLogic
{
    public class BusinessOwnerController
    {
        static BusinessOwnerController instance = null;
        static MyDataBaseDataContext db = new MyDataBaseDataContext();


        public static BusinessOwnerController getInstance()
        {
            if (instance == null)
            {
                instance = new BusinessOwnerController();
            }
            return instance;
        }


        public void addCoupon(Coupon toAdd, Coupons_for_Business cfb)
        {
            db.Coupons.InsertOnSubmit(toAdd);
            db.SubmitChanges();
            
            db.Coupons_for_Businesses.InsertOnSubmit(cfb);
            db.SubmitChanges();
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

                foreach (Business_Owner BO in db.Business_Owners)
                {
                    if ((String.Compare((BO.username).Trim(), username) == 0) && (String.Compare((BO.password).Trim(), password) == 0))
                    {
                        ret = new LoggedInUser(BO);
                    }
                }
            return ret;
        }

        public List<Business> generateBusinessesByOwner(LoggedInUser user)
        {
            var temp = (from bobo in db.Businesses_of_Business_Owners
                        where (String.Compare(bobo.Business_Owner, user.getUserName()) == 0)
                        select bobo).ToList();

            var q = (from ser in temp
                     join c in db.Businesses on ser.Business equals c.business_name
                     select c).ToList();

            return q;
        }

        public List<Coupon> generateCouponsForBusinesses(Business business)
        {
            var temp = (from cfb in db.Coupons_for_Businesses
                        where (String.Compare(cfb.Business, business.business_name) == 0)
                        select cfb).ToList();

            var q = (from ser in temp
                     join c in db.Coupons on ser.Coupon equals c.Serial_Number
                     select c).ToList();

            return q;
        }

        public List<Business> generateBusinesses()
        {

            var q = (from business in db.Businesses
                     select business).ToList();

            return q;
        }

        public Boolean TryAddNewCoupon(String SNumber)
        {
            Boolean exist = false;
            foreach (Coupon c in db.Coupons)
            {
                if ((!exist) && (c.Serial_Number == int.Parse(SNumber)))
                    exist = true;
            }
            return exist;
        }
    }
}
