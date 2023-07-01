import MainLayout from "../../component/main-layout";
import SearchIcon from "@mui/icons-material/Search";
import { useState } from "react";
import styles from "./index.module.css";

function createFakeFavouriteMentorData(
  accountId,
  name,
  imageUrl,
  expertIn,
  isVerified,
  averateRatings,
  numOfReivews,
  address,
  timeAvailable,
  priceFrom,
  priceTo
) {
  return {
    accountId,
    name,
    imageUrl,
    expertIn,
    isVerified,
    averateRatings,
    numOfReivews,
    address,
    timeAvailable,
    priceFrom,
    priceTo,
  };
}

const favouriteMentorList = [
  createFakeFavouriteMentorData(
    "mentor1",
    "Ruby Perrin",
    "https://mentoring.dreamguystech.com/reactjs/template/5cf07dabbcf2db6a07ca8336a3538cf5.jpg",
    ["Digital Marketer"],
    true,
    5.0,
    17,
    "Florida, USA",
    "Fri, 22 Mar",
    300,
    1000
  ),
  createFakeFavouriteMentorData(
    "mentor2",
    "Darren Elder",
    "https://mentoring.dreamguystech.com/reactjs/template/94e273abf364c3ed738b33dbfdd92e3e.jpg",
    ["UNIX", "Calculus", "Trigonometry"],
    true,
    4.0,
    35,
    "Newyork, USA",
    "Fri, 22 Mar",
    50,
    300
  ),
  createFakeFavouriteMentorData(
    "mentor3",
    "Deborah Angel",
    "https://mentoring.dreamguystech.com/reactjs/template/f91b1059aaad2d00d608a93abce78897.jpg",
    ["Computer Programming"],
    true,
    4.0,
    27,
    "Georgia, USA",
    "Fri, 22 Mar",
    100,
    400
  ),
  createFakeFavouriteMentorData(
    "mentor4",
    "Sofia Brient",
    "https://mentoring.dreamguystech.com/reactjs/template/6e7336e023f2e596ba44ae182688e1b9.jpg",
    ["ASP.NET", "Computer Gaming"],
    true,
    4.0,
    4,
    "Louisiana, USA",
    "Fri, 22 Mar",
    150,
    250
  ),
  createFakeFavouriteMentorData(
    "mentor1",
    "Ruby Perrin",
    "https://mentoring.dreamguystech.com/reactjs/template/5cf07dabbcf2db6a07ca8336a3538cf5.jpg",
    ["Digital Marketer"],
    true,
    5.0,
    17,
    "Florida, USA",
    "Fri, 22 Mar",
    300,
    1000
  ),
  createFakeFavouriteMentorData(
    "mentor2",
    "Darren Elder",
    "https://mentoring.dreamguystech.com/reactjs/template/94e273abf364c3ed738b33dbfdd92e3e.jpg",
    ["UNIX", "Calculus", "Trigonometry"],
    true,
    4.0,
    35,
    "Newyork, USA",
    "Fri, 22 Mar",
    50,
    300
  ),
  createFakeFavouriteMentorData(
    "mentor3",
    "Deborah Angel",
    "https://mentoring.dreamguystech.com/reactjs/template/f91b1059aaad2d00d608a93abce78897.jpg",
    ["Computer Programming"],
    true,
    4.0,
    27,
    "Georgia, USA",
    "Fri, 22 Mar",
    100,
    400
  ),
  createFakeFavouriteMentorData(
    "mentor4",
    "Sofia Brient",
    "https://mentoring.dreamguystech.com/reactjs/template/6e7336e023f2e596ba44ae182688e1b9.jpg",
    ["ASP.NET", "Computer Gaming"],
    true,
    4.0,
    4,
    "Louisiana, USA",
    "Fri, 22 Mar",
    150,
    250
  ),
  createFakeFavouriteMentorData(
    "mentor1",
    "Ruby Perrin",
    "https://mentoring.dreamguystech.com/reactjs/template/5cf07dabbcf2db6a07ca8336a3538cf5.jpg",
    ["Digital Marketer"],
    true,
    5.0,
    17,
    "Florida, USA",
    "Fri, 22 Mar",
    300,
    1000
  ),
  createFakeFavouriteMentorData(
    "mentor2",
    "Darren Elder",
    "https://mentoring.dreamguystech.com/reactjs/template/94e273abf364c3ed738b33dbfdd92e3e.jpg",
    ["UNIX", "Calculus", "Trigonometry"],
    true,
    4.0,
    35,
    "Newyork, USA",
    "Fri, 22 Mar",
    50,
    300
  ),
  createFakeFavouriteMentorData(
    "mentor3",
    "Deborah Angel",
    "https://mentoring.dreamguystech.com/reactjs/template/f91b1059aaad2d00d608a93abce78897.jpg",
    ["Computer Programming"],
    true,
    4.0,
    27,
    "Georgia, USA",
    "Fri, 22 Mar",
    100,
    400
  ),
  createFakeFavouriteMentorData(
    "mentor4",
    "Sofia Brient",
    "https://mentoring.dreamguystech.com/reactjs/template/6e7336e023f2e596ba44ae182688e1b9.jpg",
    ["ASP.NET", "Computer Gaming"],
    true,
    4.0,
    4,
    "Louisiana, USA",
    "Fri, 22 Mar",
    150,
    250
  ),
];

const Favourite = () => {
  const [searchFavourite, setSearchFavourite] = useState("");
  const [mentorList, setMentorList] = useState([...favouriteMentorList]);

  return (
    <div className={styles.layoutWrapper}>
      <MainLayout
        pageTitle={"Favourites"}
        titleControl={
          <div className={styles.inputWrapper}>
            <input
              className={styles.customInput}
              type="text"
              placeholder="Search Favourites..."
              value={searchFavourite}
              onChange={(e) => setSearchFavourite(e.target.value)}
            />
            <div className={styles.searchWrapper}>
              <SearchIcon />
            </div>
          </div>
        }
        layoutContent={<div className={styles.mentorList}></div>}
      />
    </div>
  );
};

export default Favourite;
