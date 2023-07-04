import MainAdminLayout from "../../../component/admin/main-layout";
import Statistic from "../../../component/admin/statistic";
import PeopleIcon from "@mui/icons-material/People";
import CreditCardIcon from "@mui/icons-material/CreditCard";
import StarBorderOutlinedIcon from "@mui/icons-material/StarBorderOutlined";
import FolderOutlinedIcon from "@mui/icons-material/FolderOutlined";
import ChartLayout from "../../../component/admin/chart-layout";
import styles from "./index.module.css";
import {
  Area,
  AreaChart,
  CartesianGrid,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";

const fakeRevenueData = [
  {
    year: "2013",
    Revenue: 4000,
  },
  {
    year: "2014",
    Revenue: 3000,
  },
  {
    year: "2015",
    Revenue: 2000,
  },
  {
    year: "2015",
    Revenue: 2780,
  },
  {
    year: "2016",
    Revenue: 1890,
  },
  {
    year: "2017",
    Revenue: 2390,
  },
  {
    year: "2018",
    Revenue: 3490,
  },
  {
    year: "2019",
    Revenue: 3490,
  },
];

const AdminDashboard = () => {
  return (
    <MainAdminLayout
      title="Welcome Admin"
      breadCum={[{ to: "/admin/dashboard", represent: "Dashboard" }]}
      content={
        <div className={styles.layoutWrapper}>
          <div className="row">
            <div className="col-xl-3 col-sm-6 col-12">
              <Statistic
                icon={<PeopleIcon sx={{ color: "#1e88e5" }} />}
                about={"Members"}
                percent={50}
                quantity={168}
                color={"#1e88e5"}
              />
            </div>
            <div className="col-xl-3 col-sm-6 col-12">
              <Statistic
                icon={<CreditCardIcon sx={{ color: "#3dd598" }} />}
                about={"Appointments"}
                percent={50}
                quantity={487}
                color={"#3dd598"}
              />
            </div>
            <div className="col-xl-3 col-sm-6 col-12">
              <Statistic
                icon={<StarBorderOutlinedIcon sx={{ color: "#fc5a5a" }} />}
                about={"Mentoring Points"}
                percent={50}
                quantity={485}
                color={"#fc5a5a"}
              />
            </div>
            <div className="col-xl-3 col-sm-6 col-12">
              <Statistic
                icon={<FolderOutlinedIcon sx={{ color: "#ffbc34" }} />}
                about={"Revenue"}
                percent={50}
                quantity={"$62523"}
                color={"#ffbc34"}
              />
            </div>
          </div>

          <div className="row" style={{ justifyContent: "center" }}>
            <div className="col-md-12 col-lg-10">
              <ChartLayout
                title={"Revenue"}
                content={
                  <ResponsiveContainer width="100%" height={342}>
                    <AreaChart
                      data={fakeRevenueData}
                      margin={{
                        top: 10,
                        right: 30,
                        left: 0,
                        bottom: 0,
                      }}
                    >
                      <CartesianGrid
                        horizontal={true}
                        vertical={false}
                        stroke="#ccc"
                        strokeWidth={1}
                      />
                      <XAxis dataKey="year" />
                      <YAxis />
                      <Tooltip />
                      <Area
                        type="monotone"
                        dataKey="Revenue"
                        stroke="#8884d8"
                        fill="#769cbc"
                      />
                    </AreaChart>
                  </ResponsiveContainer>
                }
              />
            </div>
          </div>
        </div>
      }
    />
  );
};

export default AdminDashboard;
