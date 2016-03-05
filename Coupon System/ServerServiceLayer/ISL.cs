using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataBase;
using ServerBusinessLogic;

namespace ServerServiceLayer
{
    public class ISL
    {
        static BusinessOwnerController BOC = BusinessOwnerController.getInstance();
        static SystemManagerController SMC = SystemManagerController.getInstance();
        static ClientController CC = ClientController.getInstance();

        public Boolean checkCouponSearchTerms(String CatID, String Serial, String Rating, String minPrice, String maxPrice, String Category)
        {
            Boolean correct = true;
            if ((!IsDigitsOnly(CatID)) || (!IsDigitsOnly(Serial)) ||
                (!IsDigitsOnly(minPrice)) || (!IsDigitsOnly(maxPrice)) || (!IsDigitsOnly(Category)))
                correct = false;

            return correct;
        }

        public Boolean OrderCoupon(Coupon c, LoggedInUser user)
        {
                return CC.OrderCoupon(c, user);
        }

        public List<Coupon> SearchCoupons(String CatID, String Serial, String Rating, String minPrice, String maxPrice, String Category)
        {
            return CC.SearchCoupons(CatID, Serial, Rating, minPrice, maxPrice, Category);
        }

        public List<Coupon> generateCoupons(LoggedInUser user)
        {
            return CC.generateCoupons(user);
        }

        public List<Coupon> generateUnApprovedCoupons()
        {
            return SMC.generateUnApprovedCoupons();
        }

        public List<Business> generateBusinessesByOwner(LoggedInUser user)
        {
            return BOC.generateBusinessesByOwner(user);
        }

        public List<Coupon> generateCouponsForBusinesses(Business business)
        {
            return BOC.generateCouponsForBusinesses(business);
        }

        public List<Business> generateBusinesses()
        {
            return CC.generateBusinesses();
        }

        public void addClient(String username, String FName, String LName, String Password, String Email, String PhoneNumber, DateTime DoB, String Gender)
        {
            Client toAdd = new Client();
            toAdd.username = username;
            toAdd.first_name = FName;
            toAdd.last_name = LName;
            toAdd.password = Password;
            toAdd.email = Email;
            toAdd.phone = PhoneNumber;
            toAdd.birth_day = DoB;
            if (String.Compare(Gender, "Male") == 0)
                toAdd.gender = 1;
            else
                toAdd.gender = 0;
            CC.addClient(toAdd);
        }

        public void addBusinessOwner(String username, String FName, String LName, String Password, String Email, String PhoneNumber, DateTime DoB, String Gender)
        {
            Business_Owner toAdd = new Business_Owner();
            toAdd.username = username;
            toAdd.first_name = FName;
            toAdd.last_name = LName;
            toAdd.password = Password;
            toAdd.email = Email;
            toAdd.phone = PhoneNumber;
            toAdd.birth_date = DoB;
            if (String.Compare(Gender, "Male") == 0)
                toAdd.gender = 1;
            else
                toAdd.gender = 0;
            SMC.addBusinessOwner(toAdd);
        }

        public void addCoupon(String cat, String CatID, String CName, String Desc, String OPrice, String DPrice, String SNumber, String Rating, DateTime LD, Business business)
        {
            Coupon toAdd = new Coupon();
            toAdd.couponCategory = int.Parse(cat);
            toAdd.categoryID = int.Parse(CatID);
            toAdd.name = CName;
            toAdd.description = Desc;
            toAdd.orginialPrice = int.Parse(OPrice);
            toAdd.discountedPrice = int.Parse(DPrice);
            toAdd.lastDate = LD;
            toAdd.Serial_Number = int.Parse(SNumber);
            toAdd.rating = int.Parse(Rating);
            toAdd.isApproved = false;

            Coupons_for_Business cfb = new Coupons_for_Business();
            cfb.Business = business.business_name;
            cfb.Coupon = toAdd.Serial_Number;
            BOC.addCoupon(toAdd, cfb);
        }

        public Boolean TryAddNewCoupon(String SNumber)
        {
            return BOC.TryAddNewCoupon(SNumber);
        }

        public Boolean TryAddNewUser(String username, String email)
        {
            return CC.TryAddNewUser(username, email);
        }

        public Boolean TryForgotPassword(String email)
        {
            return CC.TryForgotPassword(email);
        }

        public LoggedInUser TryLogin(String username, String password)
        {
            LoggedInUser ret = null;

            ret = CC.TryLogin(username, password);
            if (ret == null)
                ret = BOC.TryLogin(username, password);
            if (ret == null)
                ret = SMC.TryLogin(username, password);
            

            return ret;
        }

        public Boolean IsDigitsOnly(string str)
        {
            foreach (char c in str)
            {
                if (c < '0' || c > '9')
                    return false;
            }

            return true;
        }

        public Boolean hasNumbers(String str)
        {
            Boolean ret = false;
            for (int i = 0; i < str.Length; i++)
            {
                if (((int)str[i] >= '0') && ((int)str[i] <= '9'))
                    ret = true;
            }
            return ret;
        }

        public bool checkBusinessSearchTerms(string City, string Cat, string Name)
        {
            Boolean correct = true;
            if ((!IsDigitsOnly(Cat)) || (hasNumbers(City)) || (hasNumbers(Name)))
                correct = false;

            return correct;
        }

        public List<Business> SearchBusinesses(string City, string Cat, string Name)
        {
            return CC.SearchBusinesses(City, Cat, Name);
        }

        public bool ApproveCoupon(Coupon coupon)
        {
            return SMC.ApproveCoupon(coupon);
        }

        public bool addBusiness(string BusinessName, string Address, string City, string Description, string Category)
        {
            Business toAdd = new Business();
            toAdd.business_name = BusinessName;
            toAdd.address = Address;
            toAdd.city = City;
            toAdd.description = Description;
            toAdd.category = int.Parse(Category);
            return SMC.addBusiness(toAdd);
        }

        public bool TryRemove(Business business)
        {
            return SMC.TryRemove(business);
        }

        public Boolean checkBusinessInput(String BusinessName, String Address, String City, String Category)
        {
            Boolean correct = true;
            if ((BusinessName == "") || (Address == "") || (City == "") || (Category == "") ||
                (hasNumbers(City)) || (hasNumbers(BusinessName)) ||
                (!IsDigitsOnly(Category)))
                correct = false;

            return correct;
        }

        public bool TryEdit(Business business)
        {
            Boolean okay = false;
            if (checkBusinessInput(business.business_name, business.address, business.city, "" + business.category))
                okay = SMC.TryEdit(business);
            return okay;
        }
    }
}
