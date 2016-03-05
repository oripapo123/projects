using System;
using System.Collections.Generic;
using System.Linq;
using System.Data.SqlClient;
using System.Text;
using System.Threading.Tasks;
using DataBase;

namespace ServerBL
{
    public interface IFunc
    {
        void addCoupon(Coupon coupon);

        void approveCoupon(Coupon coupon);

        void editCoupon(Coupon coupon);

        void addBusiness(string name, string address, string city, string description, int category);

        void editBusiness(Business business);

        void deleteBusiness(Business business);

        void adduser();

        void loginUser(string username, string password);

        void sendPasswordByName(string username);

        void buyCoupon(Coupon coupon);

        void rateCoupon(Coupon coupon);

        void useCoupon(Coupon coupon);

        void notifyAboutCoupon(Coupon coupon);
    }
}
