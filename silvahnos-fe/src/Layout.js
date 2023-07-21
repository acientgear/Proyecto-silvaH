import Navbar from "./components/Navbar";

const Layout = ({ children, token }) => {
    return (
        <>
            <Navbar logged={token}/>
            <main style={{backgroundColor:"#f7f7f8"}}>{children}</main>
        </>
    );
};

export default Layout;