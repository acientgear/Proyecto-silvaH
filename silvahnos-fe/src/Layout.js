import Navbar from "./components/Navbar";

const Layout = ({ children }) => {
    return (
        <>
            <Navbar/>
            <main style={{backgroundColor: "#F7F7F8"}}>{children}</main>
        </>
    );
};

export default Layout;