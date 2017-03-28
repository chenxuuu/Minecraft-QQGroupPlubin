using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Flexlive.CQP.CSharpPlugins.Demo
{
    public partial class FormSettings : Form
    {
        public FormSettings()
        {
            InitializeComponent();

            //加载标题。
            this.Text = System.Reflection.Assembly.GetAssembly(this.GetType()).GetName().Name + " 参数设置";
        }

        /// <summary>
        /// 退出按钮事件处理方法。
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// 保存按钮事件处理方法。
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSave_Click(object sender, EventArgs e)
        {
            //参数保存处理代码。

            this.btnExit_Click(null, null);
        }

        private void button1_Click(object sender, EventArgs e)
        {
            MyPlugin.insert(1, "群号", textBox1.Text);
            MyPlugin.GroupSet = long.Parse(textBox1.Text);
            button1.Text = "设置成功";
        }

        private void button2_Click(object sender, EventArgs e)
        {
            MyPlugin.insert(1, textBox2.Text + "admin", "admin");
            button2.Text = "添加成功";
        }

        private void FormSettings_Load(object sender, EventArgs e)
        {

        }
    }
}
