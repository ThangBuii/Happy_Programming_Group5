const Hero = () => {
  return (
    <div className="container py-5">
      <h4 className="text-center">
        Not yet convinced? Don't just take our word for it{" "}
      </h4>
      <p>
        We've already delivered 1-on-1 mentorship to thousands of students,
        professionals, managers and executives.Even better, they've left an
        average rating of 4.9 out of 5 for our mentors
      </p>
      <div className="row row-cols-1 row-cols-md-3 g-4 py-5">
        <div className="col">
          <div className="card">
            <img
            style={{height: "350px"}}
              src="https://scontent.fhan4-1.fna.fbcdn.net/v/t1.15752-9/361636933_299267022461384_3965446561806222798_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=ae9488&_nc_ohc=aiJTCgWHOYIAX__GiqA&_nc_ht=scontent.fhan4-1.fna&oh=03_AdSPDA_SVhdj9ofMZSYOSSp-lsjF2p3suu5Kr_LntFpe0w&oe=64DD455E"
              className="card-img-top"
            />
            <div className="card-body">
              <h5 className="text-center">Trương Quốc Cường</h5>
              <p>
                "Tôi thấy bài giảng rất bổ ích, thầy cô support nhiệt tình."
              </p>
            </div>
          </div>
        </div>

        <div className="col">
          <div className="card">
            <img
            style={{height: "350px", width:"100%"}}
              src="https://scontent.xx.fbcdn.net/v/t1.15752-9/361648593_1424940404971483_6300012548322394860_n.jpg?stp=dst-jpg_s206x206&_nc_cat=103&ccb=1-7&_nc_sid=aee45a&_nc_ohc=OphfCCpgzRcAX86QSMf&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdRqFpBW2XCmalmHYfgXbDugW49Tp8XdW_iepG2UWd0JAQ&oe=64DD62B1"
              className="card-img-top"
            />
            <div className="card-body">
              <h5 className="text-center">Nguyễn Trọng Tài</h5>
              <p>
                "Phương pháp giảng dạy sáng tạo linh hoạt, tạo động lực "
              </p>
            </div>
          </div>
        </div>
        <div className="col">
          <div className="card">
            <img
            style={{height: "350px"}}
              src="https://scontent.fhan3-3.fna.fbcdn.net/v/t1.15752-9/361689443_793584915757961_812784876015830610_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=ae9488&_nc_ohc=avJbFjlPkiIAX_iuvW9&_nc_ht=scontent.fhan3-3.fna&oh=03_AdRcFwPE-MaRWgd_8ysOC1YGR3U-McEfquJkrSZXkfixzQ&oe=64DD3873"
              className="card-img-top"
            />
            <div className="card-body">
              <h5 className="text-center">Nguyễn Ngọc Quang</h5>
              <p>
                "Tạo điểm nhấn đặc biệt trong học tập, động viên và hỗ trợ phát triển cá nhân"
              </p>
            </div>
          </div>
        </div>

        <div className="col">
          <div className="card">
            <img
            style={{height: "350px"}}
              src="https://scontent.fhan3-2.fna.fbcdn.net/v/t1.15752-9/361160611_2320205991505386_7896741643743301093_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=ae9488&_nc_ohc=r1J186J5m2kAX-Vg5gX&_nc_ht=scontent.fhan3-2.fna&oh=03_AdTE30YhPLy5EyqA91I3T-m7HI7NjIj62WCTK0zgUE3Lgw&oe=64DD5B16"
              className="card-img-top"
            />
            <div className="card-body">
              <h5 className="text-center">Hồ Anh Dũng</h5>
              <p>
               "Sao tôi biết đến khoá học này sớm hơn"
              </p>
            </div>
          </div>
        </div>

        <div className="col">
          <div className="card">
            <img
            style={{height: "350px"}}
              src="https://scontent.fhan3-3.fna.fbcdn.net/v/t1.15752-9/358815847_6825940620827756_2781566776455992894_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=ae9488&_nc_ohc=KLpl3KtjGbcAX_oR9oq&_nc_ht=scontent.fhan3-3.fna&oh=03_AdQcFvP385XVvIWs0myBrtaKoHooHfTrDo3nF8EWWgs2Og&oe=64DD37B3"
              className="card-img-top"
            />
            <div className="card-body">
              <h5 className="text-center">Mai Anh Tuấn</h5>
              <p>
                "Tuyệt vời"
              </p>
            </div>
          </div>
        </div>

        <div className="col">
          <div className="card">
            <img
            style={{height: "350px"}}
              src="https://scontent.fhan3-5.fna.fbcdn.net/v/t1.15752-9/346151873_1650386715410413_8913755376485804450_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=ae9488&_nc_ohc=1y1nItXNVP8AX--4C2u&_nc_ht=scontent.fhan3-5.fna&oh=03_AdQfAWPiD6rB2wn2FRBr7C0bI31p4qrgfggoOqyKA8_9rg&oe=64DD5D1F"
              className="card-img-top"
            />
            <div className="card-body">
              <h5 className="text-center">Bùi Đức Thắng</h5>
              <p>
               "Tạo môi trường học tập thoải mái và tích cực"
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Hero;
