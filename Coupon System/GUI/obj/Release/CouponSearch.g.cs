﻿#pragma checksum "..\..\CouponSearch.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "3DE543C3E0AB52CCEC48196A8F204556"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace GUI {
    
    
    /// <summary>
    /// CouponSearch
    /// </summary>
    public partial class CouponSearch : System.Windows.Window, System.Windows.Markup.IComponentConnector {
        
        
        #line 10 "..\..\CouponSearch.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.DataGrid CouponGrid;
        
        #line default
        #line hidden
        
        
        #line 12 "..\..\CouponSearch.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button Order;
        
        #line default
        #line hidden
        
        
        #line 13 "..\..\CouponSearch.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox Cat;
        
        #line default
        #line hidden
        
        
        #line 14 "..\..\CouponSearch.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox MaxPrice;
        
        #line default
        #line hidden
        
        
        #line 15 "..\..\CouponSearch.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox MinPrice;
        
        #line default
        #line hidden
        
        
        #line 16 "..\..\CouponSearch.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox SNumber;
        
        #line default
        #line hidden
        
        
        #line 17 "..\..\CouponSearch.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox CatID;
        
        #line default
        #line hidden
        
        
        #line 18 "..\..\CouponSearch.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.ComboBox Rating;
        
        #line default
        #line hidden
        
        
        #line 26 "..\..\CouponSearch.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button GPS;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/GUI;component/couponsearch.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\CouponSearch.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            
            #line 8 "..\..\CouponSearch.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.Button_Click_1);
            
            #line default
            #line hidden
            return;
            case 2:
            this.CouponGrid = ((System.Windows.Controls.DataGrid)(target));
            
            #line 10 "..\..\CouponSearch.xaml"
            this.CouponGrid.SelectionChanged += new System.Windows.Controls.SelectionChangedEventHandler(this.CouponGrid_SelectionChanged);
            
            #line default
            #line hidden
            return;
            case 3:
            
            #line 11 "..\..\CouponSearch.xaml"
            ((System.Windows.Controls.Button)(target)).Click += new System.Windows.RoutedEventHandler(this.Button_Click);
            
            #line default
            #line hidden
            return;
            case 4:
            this.Order = ((System.Windows.Controls.Button)(target));
            
            #line 12 "..\..\CouponSearch.xaml"
            this.Order.Click += new System.Windows.RoutedEventHandler(this.Order_Click);
            
            #line default
            #line hidden
            return;
            case 5:
            this.Cat = ((System.Windows.Controls.TextBox)(target));
            return;
            case 6:
            this.MaxPrice = ((System.Windows.Controls.TextBox)(target));
            return;
            case 7:
            this.MinPrice = ((System.Windows.Controls.TextBox)(target));
            return;
            case 8:
            this.SNumber = ((System.Windows.Controls.TextBox)(target));
            return;
            case 9:
            this.CatID = ((System.Windows.Controls.TextBox)(target));
            return;
            case 10:
            this.Rating = ((System.Windows.Controls.ComboBox)(target));
            
            #line 18 "..\..\CouponSearch.xaml"
            this.Rating.Loaded += new System.Windows.RoutedEventHandler(this.ComboBox_Loaded);
            
            #line default
            #line hidden
            
            #line 19 "..\..\CouponSearch.xaml"
            this.Rating.SelectionChanged += new System.Windows.Controls.SelectionChangedEventHandler(this.ComboBox_SelectionChanged);
            
            #line default
            #line hidden
            return;
            case 11:
            this.GPS = ((System.Windows.Controls.Button)(target));
            
            #line 26 "..\..\CouponSearch.xaml"
            this.GPS.Click += new System.Windows.RoutedEventHandler(this.GPS_Click);
            
            #line default
            #line hidden
            return;
            }
            this._contentLoaded = true;
        }
    }
}

