import Navbar from "./components/Navbar";

const Layout = ({ children }) => {
    return (
        <>
            <Navbar/>
            <main style={{backgroundColor:"#f7f7f8"}}>{children}</main>
        </>
    );
};

export default Layout;