import { useEffect, useRef, useState } from "react";
import MainAdminLayout from "../../../component/admin/main-layout";
import { Button, CircularProgress, TextField } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import styles from "./index.module.css";

// Manage Skill : Có add, delete, edit Skill Name, Numbers of Mentor

const breadcrumbArr = [
  { to: "/admin/dashboard", represent: "Dashboard" },
  { to: "/admin/skill", represent: "Skill" },
];

const fakeRowSkillData = [
  {
    id: "skill1",
    skillName: "Python",
    numberOfMentor: 8,
  },
  {
    id: "skill2",
    skillName: "Java",
    numberOfMentor: 18,
  },
  {
    id: "skill3",
    skillName: "React",
    numberOfMentor: 9,
  },
];

const ManageSkill = () => {
  const [isConfirmDialogOpen, setIsConfirmDialogOpen] = useState(false);
  const [isShowDialogOpen, setIsShowDialogOpen] = useState(false);
  const [chooseId, setChooseId] = useState("");
  const inputRef = useRef(null);
  const [isLoading, seIsLoading] = useState(true);
  const [listIdLoading, setListIdLoading] = useState([]);
  const [snackBarState, setSnackBarState] = useState({
    isSnackBarOpen: false,
    severity: "",
    content: "",
  });
  const [skillRow, setSkillRow] = useState([]);

  useEffect(() => {
    fetch("http://localhost:9999/all-mentor")
      .then((resp) => resp.json())
      .then((data) => {
        setSkillRow(data);
      })
      .catch((err) => {
        console.log(err);
        setSkillRow([...fakeRowSkillData]);
      })
      .finally(() => {
        seIsLoading(false);
      });
  }, []);

  const onCloseSnackBar = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }

    setSnackBarState((pre) => ({
      isSnackBarOpen: false,
      severity: "",
      content: "",
    }));
  };

  const handleCloseShowDialog = () => {
    setIsShowDialogOpen(false);
    setChooseId("");
  };

  const handleCloseConfirmDialog = () => {
    setIsConfirmDialogOpen(false);
    setChooseId("");
  };

  const handleClickDelete = (id) => {
    setIsConfirmDialogOpen(true);
    setChooseId(id);
  };

  const handleAgreeDelete = async (id) => {
    handleCloseConfirmDialog(id);
    setListIdLoading((pre) => [...pre, id]);

    await fetch(`http://localhost:9999/skill/delete/${id}`)
      .then((resp) => resp.json())
      .then((data) => {
        if (data.isSuccess === 0)
          setSkillRow((pre) => pre.filter((item) => item.id !== id));
      })
      .catch((err) => {
        console.log(err);
        setSnackBarState((pre) => ({
          isSnackBarOpen: true,
          severity: "error",
          content: "Something went wrong!",
        }));
      })
      .finally(() => {
        setListIdLoading((pre) => pre.filter((item) => item !== id));
      });
  };

  const getChooseName = (rowTmp, id) => {
    if (id === "" || rowTmp.lenght === 0) return "";
    const item = rowTmp.find((item) => item.id === id);
    if (!item) return "";
    return item.skillName;
  };

  const handleConfirmAdd = async (id) => {
    setListIdLoading((pre) => [...pre, id]);

    console.log("check data: >> ", inputRef.current.value, id);

    // post ma gui data len voi inputRef.current.value ******
    await fetch(`http://localhost:9999/skill/${id}`)
      .then((resp) => resp.json())
      .then((data) => {
        if (data.isSuccess === 0) {
          setSkillRow((pre) => [
            ...pre,
            {
              id: data.id,
              skillName: inputRef.current.value,
              numberOfMentor: 0,
            },
          ]);
          handleCloseShowDialog(id);
        } else {
          // handle loi bi trung o day
          setSnackBarState((pre) => ({
            isSnackBarOpen: true,
            severity: "error",
            content: "Skill Name exist!",
          }));
        }
      })
      .catch((err) => {
        console.log(err);
        setSnackBarState((pre) => ({
          isSnackBarOpen: true,
          severity: "error",
          content: "Something went wrong!",
        }));
      })
      .finally(() => {
        setListIdLoading((pre) => pre.filter((item) => item !== id));
      });
  };

  const columns = [
    {
      field: "skillName",
      headerName: "Skill Name",
      type: "string",
      flex: 0.4,
      align: "left",
      headerAlign: "left",
      renderCell: ({ value }) => {
        return <div className={styles.skillItem}>{value}</div>;
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Skill Name"}</strong>
      ),
    },
    {
      field: "numberOfMentor",
      headerName: "Number Of Mentors",
      type: "number",
      flex: 0.4,
      align: "center",
      headerAlign: "center",
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Number Of Mentors"}</strong>
      ),
    },
    {
      field: "actions",
      headerName: "Actions",
      type: "string",
      flex: 0.2,
      align: "center",
      headerAlign: "center",
      renderCell: ({ value, row }) => {
        return (
          <div className={styles.actionWrapper}>
            <Button
              variant="contained"
              color="warning"
              onClick={() => handleClickDelete(row.id)}
              disabled={listIdLoading.includes(row.id)}
            >
              Delete
            </Button>
          </div>
        );
      },
      renderHeader: (params) => (
        <strong style={{ fontSize: "16px" }}>{"Actions"}</strong>
      ),
    },
  ];

  return (
    <MainAdminLayout
      title="List of Skills"
      breadCum={[...breadcrumbArr]}
      content={
        <>
          {isLoading ? (
            <div className={styles.customLoading}>
              <CircularProgress />
            </div>
          ) : (
            <div
              className={styles.layoutWrapper}
              style={{ height: 400, width: "100%" }}
            >
              <div className={styles.topControl}>
                <Button
                  variant="contained"
                  color="success"
                  onClick={() => setIsShowDialogOpen(true)}
                >
                  Add Skill
                </Button>
              </div>
              <DataGrid
                sx={{
                  backgroundColor: "#fff",
                  padding: "24px",
                  "& .MuiDataGrid-columnHeaders": {
                    backgroundColor: "rgb(248, 249, 250)",
                  },
                }}
                rows={skillRow}
                columns={columns}
                initialState={{
                  pagination: {
                    paginationModel: { page: 0, pageSize: 5 },
                  },
                }}
                pageSizeOptions={[5, 10]}
              />
            </div>
          )}
        </>
      }
      isConfirmDialogOpen={isConfirmDialogOpen}
      onCloseConfirmDialog={handleCloseConfirmDialog}
      confirmDialogTitle="Confirm delete mentee?"
      confirmDialogContent={`Are you sure you want to delete skill ${getChooseName(
        skillRow,
        chooseId
      )}`}
      onAgreeConfirmDialog={() => handleAgreeDelete(chooseId)}
      onDisAgreeConfirmDialog={handleCloseConfirmDialog}
      isShowDialogOpen={isShowDialogOpen}
      onCloseShowDialog={handleCloseShowDialog}
      showDialogTitle={
        <div className={styles.titleWrapper}>
          <h4>Update Report</h4>
        </div>
      }
      showDialogContent={
        <div className={styles.contentWrapper}>
          <TextField
            label="Skill Name"
            variant="outlined"
            sx={{
              width: "400px",
              marginTop: "10px",
              "& input:focus": {
                width: "auto",
                margin: "0",
                borderBottom: "0px solid",
              },
            }}
            inputRef={inputRef}
          />
          <div className={styles.botCtWrarpper}>
            <Button
              variant="contained"
              color="primary"
              onClick={() => handleConfirmAdd(chooseId)}
              disabled={listIdLoading.includes(chooseId)}
            >
              Update
            </Button>
            <Button
              variant="contained"
              color="secondary"
              onClick={handleCloseShowDialog}
            >
              Close
            </Button>
          </div>
        </div>
      }
      isSnackBarOpen={snackBarState.isSnackBarOpen}
      onCloseSnackBar={onCloseSnackBar}
      severity={snackBarState.severity}
      severityContent={snackBarState.content}
    />
  );
};

export default ManageSkill;
